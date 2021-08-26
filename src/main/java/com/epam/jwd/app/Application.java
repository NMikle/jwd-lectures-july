package com.epam.jwd.app;

import com.epam.jwd.model.Department;
import com.epam.jwd.model.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Application {

    private static final Logger LOG = LogManager.getLogger(Application.class);

    public static void main(String[] args) {
        final Department market = new Department("Marketing");
        final Department sales = new Department("Sales");
        List<Employee> marketingDept = new ArrayList<>();

        marketingDept.add(new Employee(1, "Bob", 23, market));
        marketingDept.add(new Employee(2, "Alice", 25, sales));
        marketingDept.add(new Employee(1, "Tom", 22, market));

        int count = 0;
//        count++; //breaks effectively final
        marketingDept.forEach(emp -> {
            System.out.println(emp);
            System.out.println(count);
        });

        System.out.println();

//        marketingDept.sort(Comparator.comparing(new Function<Employee, String>() {
//            @Override
//            public String apply(Employee emp) {
//                return employee.getName();
//            }
//        })); //comparator with anonymous class (for functional interface)
//        marketingDept.sort(Comparator.comparing(emp -> emp.getName()));// same as previous
        marketingDept.sort(Comparator.comparing(Employee::getDepartment));// same as previous

//        marketingDept.forEach(emp -> System.out.println(emp));
        marketingDept.forEach(System.out::println);


//        for (Employee employee : marketingDept) {
//            System.out.println(employee);
//        }

//        Set<Color> colors = EnumSet.allOf(Color.class);
//        for (Color color : colors) {
//            System.out.println(color);
//        }

//        final List<Integer> ints = Arrays.asList(3, 4, 5);//Immutable collection

//        doSomething(i -> String.valueOf(i));
//        doSomething(String::valueOf);
//        doSomething(Application::convertAndAddSymbols);
    }

    private static void doSomething(CustomFunctionalInterface functionalInterface) {
        final String result = functionalInterface.theOnlyAbstractMethod(4);
        System.out.printf("This was converted to string: %s.\n", result);
    }

    private static String convertAndAddSymbols(int value) {
        return "\"" + value + "\"";
    }

    private interface CustomFunctionalInterface {

        String theOnlyAbstractMethod(int i);

    }
}
