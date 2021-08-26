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
//        marketingDept = Collections.unmodifiableList(marketingDept); //will make marketingDept unmodifiable
        marketingDept.sort(Comparator.comparing(Employee::getDepartment));// same as previous


        for (Employee employee : marketingDept) {
            System.out.println(employee);
        }

//        Set<Color> colors = EnumSet.allOf(Color.class);
//        for (Color color : colors) {
//            System.out.println(color);
//        }

//        final List<Integer> ints = Arrays.asList(3, 4, 5);//Immutable collection
    }

    private static int countElements(List<? extends Employee> employees, Department department) {
        int count = 0;
//        for (T employee : employees) {
//            if (employee.getDepartment().getName().equals(department.getName())) {
//                count++;
//            }
//        } // <T extends Employee> List<T>
        for (Employee employee : employees) {
            if (employee.getDepartment().getName().equals(department.getName())) {
                count++;
            }
        }
//        final Employee employee = employees.get(3); //will compile
//        employees.add(employee); //will not compile
        return count;
    }

    public static void consumerMethod(List<? super Employee> dest) {
        dest.add(new Employee(1, "", 1, null));
    }
}
