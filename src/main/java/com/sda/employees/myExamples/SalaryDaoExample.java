package com.sda.employees.myExamples;

import com.sda.employees.dao.SalaryDao;
import com.sda.employees.dao.SalarySQLDao;
import com.sda.employees.model.Salary;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SalaryDaoExample {
    private static final String url = "jdbc:mysql://localhost/employees";
    private static final String user = "root";
    private static final String password = "";

    public static void main(String[] args) {
        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            SalaryDao salaries = new SalarySQLDao(conn);

/*            Salary sal = salaries.get(10001);
            while ()
            System.out.println("Salaries of " + sal.toString());*/

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
