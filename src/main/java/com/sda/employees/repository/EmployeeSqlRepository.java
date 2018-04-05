package com.sda.employees.repository;

import com.sda.employees.model.Employee;
import com.sda.employees.model.Gender;

import javax.sql.DataSource;
import java.sql.*;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

public class EmployeeSqlRepository implements EmployeeRepository{

    private DataSource ds;
    private final String insertStatement = "INSERT INTO employees(emp_no, birth_date, first_name, last_name, gender, hire_date) " +
            "VALUES(?, ?, ?, ?, ?, ?)";
    public EmployeeSqlRepository(DataSource ds) {
        this.ds = ds;
    }

    public void add(Employee newEmployee) {
        try(Connection conn = ds.getConnection(); PreparedStatement insert = conn.prepareStatement(insertStatement)) {
            insert.setInt(1, newEmployee.getId());
            insert.setDate(2, newEmployee.getBirthDate());
            insert.setString(3, newEmployee.getFirstName());
            insert.setString(4, newEmployee.getLastName());
            if(newEmployee.getGender() == Gender.FEMALE) {
                insert.setString(5, "F");
            } else {
                insert.setString(5, "M");
            }
            insert.setDate(6, newEmployee.getHireDate());
            insert.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("SQL error has occurred.");
        }
    }

    public List<Employee> findAll() {
        try(Connection conn = ds.getConnection(); Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM employees");
            return readEmployeeList(rs);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("SQL error has occured.");
        }
    }

    public List<Employee> findByLastName(String lastName) {
        String query = "SELECT * FROM employees WHERE last_name = ?";
        try(Connection conn = ds.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, lastName);
            ResultSet rs = stmt.executeQuery();
            return readEmployeeList(rs);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("SQL error has occured.");
        }
    }

    // ZAD: otrzymanie listy pracowników pracujących w danym departamencie w zadanym dniu
    public List<Employee> findListEmpFromDeptInDay(String deptName, Date date){
        String query = "SELECT e.emp_no, e.first_name, e.last_name, e.birth_date," +
                " e.hire_date, e.gender, d.dept_name " +
                "FROM employees e JOIN dept_emp de ON e.emp_no = de.emp_no " +
                "JOIN departments d ON de.dept_no = d.dept_no " +
                "WHERE d.dept_name = ? " +
                "AND de.from_date <= ? AND (de.to_date >= ? OR de.to_date = '9999-01-01')";
        try(Connection conn = ds.getConnection();
            PreparedStatement find = conn.prepareStatement(query)){
            find.setString(1, deptName);
            find.setDate(2, date);
            find.setDate(3, date);
            ResultSet rs = find.executeQuery();
            return readEmployeeList(rs);
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException("SQL error has occured.");
        }
    }

    //ZAD: znalezienie listy pracowników pełniących (w dowolnym czasie) funkcję managera
    public List<Employee> findListManagerEmp(){
        String query = "SELECT e.emp_no, e.first_name, e.last_name, e.birth_date," +
                "e.hire_date, e.gender FROM employees e " +
                "JOIN dept_manager m ON e.emp_no = m.emp_no ";
        try(Connection conn = ds.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)){
            ResultSet rs = stmt.executeQuery();
            return readEmployeeList(rs);
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException("SQL error has occured.");
        }
    }

    private List<Employee> readEmployeeList(ResultSet rs) {
        ArrayList<Employee> result = new ArrayList<>();
        try {
            while (rs.next()) result.add(readEmployee(rs));
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error when reading ResultSet.");
        }
    }

    private Employee readEmployee(ResultSet rs) {
        try {
            return new Employee(
                    rs.getInt("emp_no"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getDate("birth_date"),
                    rs.getDate("hire_date"),
                    Gender.fromInitial(rs.getString("gender")));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Invalid data in ResultSet; cannot read the Employee.");
        }
    }
}
