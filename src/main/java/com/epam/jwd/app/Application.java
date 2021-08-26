package com.epam.jwd.app;

import com.epam.jwd.model.Department;
import com.epam.jwd.model.Employee;
import com.epam.jwd.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toSet;

public class Application {

    private static final Logger LOG = LogManager.getLogger(Application.class);

    public static void main(String[] args) {
        final Department market = new Department("Marketing");
        final Department sales = new Department("Sales");
        final Department it = new Department("IT");
        List<Employee> employees = new ArrayList<>();

        employees.add(new Employee(1, "Bob", 23, market));
        employees.add(new Employee(2, "Bob", 27, market));
        employees.add(new Employee(3, "Alice", 25, sales));
        employees.add(new Employee(4, "Tom", 22, market));
        employees.add(new Employee(5, "Rob", 21, sales));
        employees.add(new Employee(6, "Mark", 11, sales));
        employees.add(new Employee(7, "Mary", 65, it));
        employees.add(new Employee(8, "Alex", 81, it));

        final Map<Department, Set<Employee>> employeesByDepartments = employees.stream()
                .collect(groupingBy(Employee::getDepartment, toSet()));

        employeesByDepartments.keySet().forEach(key -> {
            employeesByDepartments.get(key).forEach(System.out::println);
            System.out.println();
        });

//        marketingDept.forEach(System.out::println);

        final Date start = new Date();
        final Set<String> names = employees.stream()
                .filter(emp -> emp.getAge() > 22)
                .map(User::getName)
                .collect(toSet());

//        final Set<String> names = new HashSet<>();
//        for (Employee emp : employees) {
//            if (emp.getAge() > 22) {
//                String name = emp.getName();
//                names.add(name);
//            }
//        }
        System.out.printf("time passed: %s\n", new Date().getTime() - start.getTime());

        names.forEach(System.out::println);
    }

}
