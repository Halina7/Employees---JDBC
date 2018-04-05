package com.sda.employees.repository;

import com.sda.employees.model.Department;
import com.sda.employees.model.DeptManager;
import com.sda.employees.model.Employee;

import java.sql.Date;
import java.util.List;

public interface DepartmentRepository {

    void add(Department dept, Integer emp_noManager);
    List<Department> findAll();
}
