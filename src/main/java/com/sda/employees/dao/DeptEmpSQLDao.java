package com.sda.employees.dao;

import com.sda.employees.model.DeptEmp;
import com.sun.xml.internal.ws.client.dispatch.PacketDispatch;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeptEmpSQLDao implements DeptEmpDao {

    Connection conn;
    private static final String deptEmpQuery = "SELECT * FROM dept_emp WHERE emp_no = ?";
    private static final String insertStmt = "INSERT INTO dept_emp " +
            "(emp_no, dept_no, from_date, to_date) VALUES (?, ?, ?, ?)";
    private static final String deleteStmt = "DELETE FROM dept_emp WHERE emp_no = ? AND dept_no = ?";
    private static final String updateStmt = "UPDATE dept_emp " +
            "SET dept_no = ?, from_date = ?, to_date = ? WHERE emp_no = ?";

    public DeptEmpSQLDao(Connection conn) {
        this.conn = conn;
    }

    @Override
    public DeptEmp get(Integer empId) throws SQLException {
        try(PreparedStatement get = conn.prepareStatement(deptEmpQuery)){
            get.setInt(1,empId);
            ResultSet rs = get.executeQuery();
            if (rs.next()){
                return new DeptEmp(empId,
                        rs.getString("dept_no"),
                        rs.getDate("from_date"),
                        rs.getDate("to_date"));
            }else {
                throw new RuntimeException("No Dept_Emp with given Id found");
            }
        }
    }

    @Override
    public void add(DeptEmp newDeptEmp) throws SQLException {
        try (PreparedStatement insert = conn.prepareStatement(insertStmt)){
            insert.setInt(1, newDeptEmp.getEmp_no());
            insert.setString(2, newDeptEmp.getDept_no());
            insert.setDate(3, newDeptEmp.getFrom_date());
            insert.setDate(4, newDeptEmp.getTo_date());
            if (insert.executeUpdate() != 1){
                throw new RuntimeException("Failed to add DeptEmp!");
            }
        }
    }

    @Override
    public void delete(DeptEmp deptEmp) throws SQLException {
        try (PreparedStatement delete = conn.prepareStatement(deleteStmt)) {
            delete.setInt(1,deptEmp.getEmp_no());
            delete.setString(2,deptEmp.getDept_no());
            if(delete.executeUpdate() == 0){
                throw new RuntimeException("No such an employee in this department!");
            }
        }
    }

    @Override
    public void update(DeptEmp deptEmp) throws SQLException {
        try (PreparedStatement update = conn.prepareStatement(updateStmt)){
            update.setString(1, deptEmp.getDept_no());
            update.setDate(2, deptEmp.getFrom_date());
            update.setDate(3, deptEmp.getTo_date());
            update.setInt(4, deptEmp.getEmp_no());
            if (update.executeUpdate() != 1){
                throw new RuntimeException("Update DeptEmp failed");
            }
        }
    }
}
