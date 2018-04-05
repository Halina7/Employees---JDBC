package com.sda.employees.dao;

import com.sda.employees.model.DeptEmp;
import com.sda.employees.model.Employee;

import java.sql.SQLException;

public interface DeptEmpDao {

    DeptEmp get(Integer empId) throws SQLException;
    void add(DeptEmp newDeptEmp) throws SQLException;
    void delete(DeptEmp deptEmp) throws SQLException;
    void update(DeptEmp deptEmp) throws SQLException;
}
