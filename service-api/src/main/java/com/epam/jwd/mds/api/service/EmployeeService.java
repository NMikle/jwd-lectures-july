package com.epam.jwd.mds.api.service;

import com.epam.jwd.mds.api.model.Department;
import com.epam.jwd.mds.api.model.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeService extends GeneralService<Employee> {

    Map<Department, List<Employee>> findEmployeesByDepartments();

}
