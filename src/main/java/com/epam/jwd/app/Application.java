package com.epam.jwd.app;

import com.epam.jwd.model.Department;
import com.epam.jwd.model.Employee;
import com.epam.jwd.model.Tail;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

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

        for (Map.Entry<Department, Set<Employee>> employeesByDep : employeesByDepartments.entrySet()) {
            System.out.println(employeesByDep.getKey());
            System.out.println(employeesByDep.getValue());
            System.out.println();
        }

//        for (Employee employee : employees) {
////            employees.remove(1); //ConcurrentModificationException
//            System.out.println(employee);
//        }

        Set<Test> a = new HashSet<>();
        a.add(new Test());
        a.add(new Test());
        System.out.println(a.size()); // 2
    }

    private static class Test {
        @Override
        public int hashCode() {
            return 5;
        }
    }
}
