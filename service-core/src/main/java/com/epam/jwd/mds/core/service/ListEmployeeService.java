package com.epam.jwd.mds.core.service;

import com.epam.jwd.mds.api.exception.CouldNotCreateEntityException;
import com.epam.jwd.mds.api.model.Department;
import com.epam.jwd.mds.api.model.Employee;
import com.epam.jwd.mds.api.service.EmployeeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;

public class ListEmployeeService implements EmployeeService {

    private static final Logger LOGGER = LogManager.getLogger(ListEmployeeService.class);

    private final Lock lock;
    private final List<Employee> employees;
    private long size;

    public ListEmployeeService() {
        LOGGER.info("List Employee Service created");
        this.lock = new ReentrantLock();
        this.employees = new ArrayList<>();
        this.size = 0;
    }

    public ListEmployeeService(List<Employee> employees) {
        LOGGER.info("List Employee Service alternative constructor called");
        this.lock = new ReentrantLock();
        this.employees = employees;
        this.size = employees.size();
    }

    @Override
    public Map<Department, List<Employee>> findEmployeesByDepartments() {
        try {
            lock.lock();
            LOGGER.trace("retrieving employees by departments");
            return retreiveEmployeesByDepartments();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public Employee save(Employee entity) throws CouldNotCreateEntityException {
        try {
            lock.lock();
            LOGGER.trace("save user method called");
            checkEmployeeNameDuplicates(entity);
            return saveEmployee(entity);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public List<Employee> findAll() {
        try {
            lock.lock();
            LOGGER.trace("retrieving all employees");
            return new ArrayList<>(employees);
        } finally {
            lock.unlock();
        }
    }

    private void checkEmployeeNameDuplicates(Employee entity) throws CouldNotCreateEntityException {
        LOGGER.debug("checking employee name duplicates");
        for (Employee employee : employees) {
            if (employee.name().equals(entity.name())) {
                LOGGER.warn("found duplicate employee. Employee: {}", employee);
                throw new CouldNotCreateEntityException("Employee with such name already exists", Employee.class, employee);
            }
        }
        LOGGER.trace("no duplicate employees found");
    }

    private Employee saveEmployee(Employee entity) {
        LOGGER.debug("saving employee");
        final Employee employee = new Employee(++size, entity.name(), entity.department());
        employees.add(employee);
        LOGGER.trace("employee saved. Employee: {}", employee);
        return employee;
    }

    private Map<Department, List<Employee>> retreiveEmployeesByDepartments() {
        return employees.stream()
                .collect(
                        groupingBy(
                                Employee::department,
                                mapping(identity(), toList())
                        )
                );
    }
}
