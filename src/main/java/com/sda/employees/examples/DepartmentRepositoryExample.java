package com.sda.employees.examples;

import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;
import com.sda.employees.model.Department;
import com.sda.employees.model.Employee;
import com.sda.employees.model.Gender;
import com.sda.employees.repository.DepartmentRepository;
import com.sda.employees.repository.DepartmentSqlRepository;

import java.sql.Date;

public class DepartmentRepositoryExample {
    public static void main(String[] args) {
        MysqlConnectionPoolDataSource ds = new MysqlConnectionPoolDataSource();
        ds.setUrl("jdbc:mysql://localhost/employees");
        ds.setUser("root");
        ds.setPassword("");
        DepartmentRepository depRepository = new DepartmentSqlRepository(ds);

        depRepository.add(new Department("d300","Kakao"), 10001);

        depRepository.findAll().stream().limit(10).forEach(System.out::println);

    }
}
