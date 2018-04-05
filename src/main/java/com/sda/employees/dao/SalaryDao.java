package com.sda.employees.dao;

import com.sda.employees.model.Salary;

import java.sql.SQLException;

public interface SalaryDao {

    Salary get(Integer empId) throws SQLException;
    void add(Salary newSal) throws SQLException;
    void delete(Salary sal) throws SQLException;
    void update(Salary sal) throws SQLException;
}
