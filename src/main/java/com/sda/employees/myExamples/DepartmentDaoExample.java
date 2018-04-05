package com.sda.employees.myExamples;

import com.sda.employees.dao.DepartmentDao;
import com.sda.employees.dao.DepartmentSqlDao;
import com.sda.employees.model.Department;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DepartmentDaoExample {

    private static final String url = "jdbc:mysql://localhost/employees";
    private static final String user = "root";
    private static final String password = "";

    public static void main(String[] args) {
        try(Connection conn = DriverManager.getConnection(url, user, password)){
            DepartmentDao departments = new DepartmentSqlDao(conn);

            Department deptToGet = departments.get("d008");
            System.out.println("Department d008: "+ deptToGet.getDept_name().toString());

            Department newDep = new Department("d250", "Challenge");
            departments.add(newDep);
            System.out.println("New department: " + newDep.toString());

            newDep.setDept_name("Wyzwanie");
            departments.update(newDep);
            System.out.println("Changed department: " + newDep.toString());

            departments.delete(newDep);
            System.out.println("Department no " + newDep.getDept_no() + " deleted");

/*            Department depToUpdate = departments.get("d250");
            depToUpdate.setDept_name("Kaczuszki");
            depToUpdate.setDept_no("d251");
            departments.update(depToUpdate);
            System.out.println("Changed department: " + depToUpdate.toString());*/

/*            newDep.setDept_name("Koziołki");
            newDep.setDept_no("d251");
            departments.update(newDep);
            System.out.println("Changed new department: " + newDep.toString());*/

/*            Department depToDelete = departments.get("d250");
            departments.delete(depToDelete);
            System.out.println("Department no " + depToDelete.getDept_no() + "deleted");*/

//            departments.delete(newDep);

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
