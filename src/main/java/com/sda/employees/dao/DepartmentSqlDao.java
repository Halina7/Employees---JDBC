package com.sda.employees.dao;

import com.sda.employees.model.Department;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DepartmentSqlDao implements DepartmentDao{

    private Connection conn;
    private static final String depQuerry = "SELECT * FROM departments WHERE dept_no = ?";
    private static final String insertStmt = "INSERT INTO departments(dept_no, dept_name) " +
            "VALUES (?, ?)";
    private static final String deleteStmt = "DELETE FROM departments WHERE dept_no = ?";
    private static final String updateStmt = "UPDATE departments SET dept_name =? WHERE dept_no = ?";

    public DepartmentSqlDao(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Department get(String deptId) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(depQuerry)) {
            stmt.setString(1, deptId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Department(deptId, rs.getString("dept_name"));
            } else {
                throw new RuntimeException("No Deepartment with given Id found");
            }
        }
    }

    @Override
    public void add(Department newDep) throws SQLException {
        try(PreparedStatement insert = conn.prepareStatement(insertStmt)){
            insert.setString(1,newDep.getDept_no());
            insert.setString(2,newDep.getDept_name());
            if(insert.executeUpdate() != 1){
                throw new RuntimeException("Failed to add Department");
            }
        }
    }

    @Override
    public void delete(Department dep) throws SQLException {
        try(PreparedStatement delete = conn.prepareStatement(deleteStmt)){
            delete.setString(1,dep.getDept_no());
            if (delete.executeUpdate() == 0){
                throw new RuntimeException("No such department in database");
            }
        }
    }

    @Override
    public void update(Department dep) throws SQLException {
        try (PreparedStatement update = conn.prepareStatement(updateStmt)){
            update.setString(1,dep.getDept_name());
            update.setString(2,dep.getDept_no());
            if (update.executeUpdate() != 1){
                throw new RuntimeException("Update failed");
            }
        }
    }
}
