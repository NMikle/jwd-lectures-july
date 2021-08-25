package com.epam.jwd.app;

import com.epam.jwd.model.Animal;
import com.epam.jwd.model.Department;
import com.epam.jwd.model.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Application {

    private static final Logger LOG = LogManager.getLogger(Application.class);

    public static void main(String[] args) {
        final Department market = new Department("Marketing");
        final Department sales = new Department("Sales");
        final Department preSales = new Department("Pre-Sales");
        final Department it = new Department("IT");

        Map<Department, Set<Employee>> employeesByDepartments = new HashMap<>();

        Set<Employee> employeesFromSalesDept = new HashSet<>();
        Set<Employee> employeesFromMarketingDept = new HashSet<>();
        Set<Employee> employeesFromItDept = new HashSet<>();

        employeesFromMarketingDept.add(new Employee(1, "Bob", 23, market));
        employeesFromMarketingDept.add(new Employee(2, "Alice", 25, market));
        employeesFromMarketingDept.add(new Employee(1, "Tom", 22, market));

        employeesFromSalesDept.add(new Employee(3, "Rob", 21, sales));
        employeesFromSalesDept.add(new Employee(4, "Mark", 11, sales));

        employeesFromItDept.add(new Employee(4, "Mary", 65, it));
        employeesFromItDept.add(new Employee(4, "Alex", 81, it));

        employeesByDepartments.put(it, employeesFromItDept);
        employeesByDepartments.put(market, employeesFromMarketingDept);
        employeesByDepartments.put(sales, employeesFromSalesDept);
        employeesByDepartments.put(preSales, new HashSet<>());

        final List<Employee> marketingSorted = new ArrayList<>(employeesFromMarketingDept);
        for (Employee employee : marketingSorted) {
            System.out.println(employee);
        }
        System.out.println();
        Collections.sort(marketingSorted);
        for (Employee employee : marketingSorted) {
            System.out.println(employee);
        }

        Comparator<String> stringComparator = Comparator.naturalOrder();
        Comparator<Employee> employeeComparator = Comparator.naturalOrder();
        final Comparator<Employee> reversed = employeeComparator.reversed();
        employeeComparator.compare(new Employee(1, "A", 1, sales), new Employee(2, "B", 2, market));
//        Comparator<Animal> animalComparator = Comparator.naturalOrder();

//        for (Map.Entry<Department, Set<Employee>> employeesByDep : employeesByDepartments.entrySet()) {
//            System.out.println(employeesByDep.getKey());
//            System.out.println(employeesByDep.getValue());
//            System.out.println();
//        }

//        for (Employee employee : employees) {
////            employees.remove(1); //ConcurrentModificationException
//            System.out.println(employee);
//        }
    }

    private static class Test {
        @Override
        public int hashCode() {
            return 5;
        }
    }
}
