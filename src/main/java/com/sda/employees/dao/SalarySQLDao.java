package com.sda.employees.dao;

import com.sda.employees.model.Salary;
import com.sun.org.apache.regexp.internal.RE;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SalarySQLDao implements SalaryDao{

    private Connection conn;

    private static final String salQuery = "SELECT * FROM salaries WHERE emp_no = ? " +
            "ORDER BY from_date";
    private static final String insertStmt = "INSERT INTO salaries " +
            "(emp_no, salary, from_date, to_date) VALUES (?, ?, ?, ?)";
    private static final String deleteStmt = "DELETE FROM salaries " +
            "WHERE emp_no = ? AND from_date = ?, AND to_date = ?";
    private static final String updateStmt = "UPDATE salaries " +
            "SET salary = ?, from_date = ?, to_date = ? WHERE emp_no = ?";

    public SalarySQLDao(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Salary get(Integer empId) throws SQLException {
        try(PreparedStatement get = conn.prepareStatement(salQuery)){
            get.setInt(1, empId);
            ResultSet rs = get.executeQuery();
            if(rs.next()){
                return new Salary(empId,
                                rs.getInt("salary"),
                                rs.getDate("from_date"),
                                rs.getDate("to_date"));
            }else{
                throw new RuntimeException("No salary for this employee with given Id found!");
            }
        }
    }

    @Override
    public void add(Salary newSal) throws SQLException {
        try(PreparedStatement insert = conn.prepareStatement(insertStmt)){
            insert.setInt(1,newSal.getEmp_no());
            insert.setInt(2,newSal.getSalary());
            insert.setDate(3,newSal.getFrom_date());
            insert.setDate(4,newSal.getTo_date());
            if(insert.executeUpdate() != 1){
                throw new RuntimeException("Failed to add Salary!");
            }
        }
    }

    @Override
    public void delete(Salary sal) throws SQLException {
        try (PreparedStatement delete = conn.prepareStatement(deleteStmt)){
            delete.setInt(1,sal.getEmp_no());
            delete.setDate(2, sal.getFrom_date());
            delete.setDate(3,sal.getTo_date());
            if (delete.executeUpdate() == 0){
                throw new RuntimeException("There is no salary for this employee in this time!");
            }
        }
    }

    @Override
    public void update(Salary sal) throws SQLException {
        try (PreparedStatement update = conn.prepareStatement(updateStmt)){
            update.setInt(1,sal.getSalary());
            update.setDate(2,sal.getFrom_date());
            update.setDate(3,sal.getTo_date());
            update.setInt(4,sal.getEmp_no());
            if (update.executeUpdate() != 1){
                throw new RuntimeException("Failed to update Salary!");
            }
        }
    }
}
