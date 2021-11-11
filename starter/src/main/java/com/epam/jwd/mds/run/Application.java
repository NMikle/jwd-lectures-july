package com.epam.jwd.mds.run;

import com.epam.jwd.mds.api.exception.CouldNotCreateEntityException;
import com.epam.jwd.mds.api.model.Department;
import com.epam.jwd.mds.api.model.Employee;
import com.epam.jwd.mds.api.service.EmployeeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ServiceLoader;

public class Application {

    private static final Logger LOGGER = LogManager.getLogger(Application.class);

    public static void main(String[] args) {
        final EmployeeService service = createEmployeeService();
        final Department sales = new Department("sales");
        final Department market = new Department("marketing");
        saveEmployee(new Employee("Bob", sales), service);
        saveEmployee(new Employee("Tom", market), service);
        saveEmployee(new Employee("Alice", sales), service);
        saveEmployee(new Employee("Bob", market), service);
        saveEmployee(new Employee("Mark", sales), service);

        LOGGER.info(service.findAll());
        LOGGER.info("-----------------------");
        LOGGER.info(service.findEmployeesByDepartments());
    }

    private static void saveEmployee(Employee employee, EmployeeService service) {
        try {
            service.save(employee);
        } catch (CouldNotCreateEntityException e) {
            LOGGER.error("attempt to save employee did not succeed", e);
            LOGGER.error("employee: {}", e.getParam());
        }
    }

    private static EmployeeService createEmployeeService() {
        return ServiceLoader.load(EmployeeService.class)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Could not find EmployeeService implementation"));
    }

}
