package com.epam.jwd.app;

import com.epam.jwd.model.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;
import java.util.function.Consumer;

public class Application {

    private static final Logger LOG = LogManager.getLogger(Application.class);

    public static void main(String[] args) {
        final Optional<String> possiblyNullHelloWorld = createString();
        if (possiblyNullHelloWorld.isPresent()) {
            final String importantString = possiblyNullHelloWorld.get();
            System.out.println(importantString);
        }
        final String employeeName = findEmployee()
                .map(Employee::getName)
                .orElseGet(Application::defaultEmployeeName);
        System.out.println(employeeName);
        badOptionalUsage(null);
    }

    private static Optional<Employee> findEmployee() {
        return Optional.ofNullable(createEmployee());
    }

    private static Optional<String> createString() {
        return Optional.of("hello world");
    }

    private static Employee createEmployee() {
//        return new Employee(1, "Alex", 14, null);
        return null;
    }

    private static Optional<String> createNothing() {
        return Optional.empty();
    }

    private static String defaultEmployeeName() {
        System.out.println("defaultEmployeeName func called");
        return "John Doe";
    }


    private static void badOptionalUsage(Optional<String> badParam) {
        if (badParam != null && badParam.isPresent()) {
            final String s = badParam.get();
        }
    }

    private static Consumer<String> test() {
        return x -> {
            System.out.println("first consumer");
            System.out.println(x);
        };
    }

}
