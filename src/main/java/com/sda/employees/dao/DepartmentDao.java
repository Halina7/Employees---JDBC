package com.sda.employees.dao;

import com.sda.employees.model.Department;
import com.sda.employees.model.Employee;

import java.sql.SQLException;

public interface DepartmentDao {

    Department get(String deptId) throws SQLException;
    void add(Department newDep) throws SQLException;
    void delete(Department dep) throws SQLException;
    void update(Department dep) throws SQLException;
}
