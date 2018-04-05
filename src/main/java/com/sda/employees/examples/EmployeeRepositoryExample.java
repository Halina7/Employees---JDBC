package com.sda.employees.examples;

import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;
import com.sda.employees.model.Employee;
import com.sda.employees.model.Gender;
import com.sda.employees.repository.EmployeeRepository;
import com.sda.employees.repository.EmployeeSqlRepository;

import javax.sql.DataSource;
import java.sql.Date;

public class EmployeeRepositoryExample {

    public static void main(String[] argv) {
        MysqlConnectionPoolDataSource ds = new MysqlConnectionPoolDataSource();
        ds.setUrl( "jdbc:mysql://localhost/employees" );
        ds.setUser("root");
        ds.setPassword("");
        EmployeeRepository repository = new EmployeeSqlRepository(ds);
        //repository.findAll().stream().limit(10).forEach(System.out::println);

        //repository.findByLastName("Acton").stream().limit(10).forEach(System.out::println);

        //repository.findListEmpFromDeptInDay("Marketing", Date.valueOf("1998-03-12"))
        //        .stream().limit(10).forEach(System.out::println);

        //repository.findListManagerEmp().stream().limit(10).forEach(System.out::println);
        repository.add(new Employee(800000,"Jan","Kowalski",
                Date.valueOf("1980-01-30"), Date.valueOf("2012-03-01"), Gender.MALE));
        repository.findByLastName("Kowalski").stream().limit(10).forEach(System.out::println);
    }
}
