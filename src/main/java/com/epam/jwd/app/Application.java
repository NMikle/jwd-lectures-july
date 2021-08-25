package com.epam.jwd.app;

import com.epam.jwd.model.Department;
import com.epam.jwd.model.Employee;
import com.epam.jwd.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public class Application {

    private static final Logger LOG = LogManager.getLogger(Application.class);

    public static void main(String[] args) {
        final Department market = new Department("Marketing");
        final Department sales = new Department("Sales");
        final List<Employee> marketingDept = new ArrayList<>();

        marketingDept.add(new Employee(1, "Bob", 23, market));
        marketingDept.add(new Employee(2, "Alice", 25, market));
        marketingDept.add(new Employee(1, "Tom", 22, market));

        for (Employee employee : marketingDept) {
            System.out.println(employee);
        }

        System.out.println();

//        marketingDept.sort(Comparator.comparing(new Function<Employee, String>() {
//            @Override
//            public String apply(Employee employee) {
//                return employee.getName();
//            }
//        })); //comparator with anonymous class (for functional interface)
//        marketingDept.sort(Comparator.comparing(emp -> emp.getName()));// same as previous
        marketingDept.sort(Comparator.comparing(Employee::getDepartment));// same as previous


        for (Employee employee : marketingDept) {
            System.out.println(employee);
        }
    }

    private static class Test {
        @Override
        public int hashCode() {
            return 5;
        }
    }
}
