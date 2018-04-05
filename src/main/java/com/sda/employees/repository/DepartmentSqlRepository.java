package com.sda.employees.repository;


import com.sda.employees.model.Department;
import com.sda.employees.model.DeptManager;
import com.sun.org.apache.regexp.internal.RE;
import sun.dc.pr.PRError;
import sun.util.calendar.BaseCalendar;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 Dodać repozytorium dla encji Departamentu, posiadające nastęujące funkcjonalności:
 - pobranie listy wszystkich departamentów
 - dodanie Departamentu
 - ustawienie menadżera departamentu w danym okresie.
 */
public class DepartmentSqlRepository implements DepartmentRepository{

private DataSource ds;
private final String insertStatement = "INSERT INTO departments (dept_no, dept_name) VALUES (?, ?)";
private final String insertDeptManager = "INSERT INTO dept_manager " +
        "(dept_no, emp_no, from_date, to_date) VALUES (?, ?, ?, ?)";

    public DepartmentSqlRepository(DataSource ds) {
        this.ds = ds;
    }

    //dodanie departamentu wraz z ustawnieniem dla niego menagera
    @Override
    public void add(Department dept, Integer emp_noManager) {
        try(Connection conn = ds.getConnection();
            PreparedStatement insert = conn.prepareStatement(insertStatement)){
            insert.setString(1,dept.getDept_no());
            insert.setString(2,dept.getDept_name());
            insert.executeUpdate();
            addManagerInTime(dept.getDept_no(), emp_noManager,Date.valueOf(LocalDate.now()) );
            }catch (SQLException e){
                e.printStackTrace();
                throw new RuntimeException("SQL error has occured");
            }
    }
    //"2018-01-01"
    // dodanie menadżera departamentu w danym okresie
    private void addManagerInTime(String dept_no, Integer emp_no, Date fromDate) {
        try(Connection conn = ds.getConnection();
            PreparedStatement insert = conn.prepareStatement(insertDeptManager)){
            insert.setString(1, dept_no);
            insert.setInt(2, emp_no);
            insert.setDate(3, fromDate);
            insert.setDate(4, Date.valueOf("9999-01-01"));
            insert.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException("SQL error has occured");
        }
    }

// pobranie listy wszystkich departamentów
    @Override
    public List<Department> findAll() {
        try (Connection conn = ds.getConnection();
            Statement stmt = conn.createStatement()){
            ResultSet result = stmt.executeQuery("SELECT * FROM departments");
            return readDepartmentList(result);
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException("SQL error has occured");
        }
    }

    private List<Department> readDepartmentList(ResultSet result) {
        ArrayList<Department> list = new ArrayList<>();
        try {
            while (result.next()){
                list.add(readDepertment(result));
            }
            return list;
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Error when reading ResultSet");
        }
    }

    private Department readDepertment(ResultSet result) {
        try {
            return new Department(result.getString("dept_no"),
                                  result.getString("dept_name"));

        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Invalid data in ResultSet, cannot read the department");
        }
    }
}
