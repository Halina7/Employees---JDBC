package com.sda.employees.model;

import java.sql.Date;

public class DeptEmp {
    private Integer emp_no;
    private String dept_no;
    private Date from_date;
    private Date to_date;

    public DeptEmp(Integer emp_no, String dept_no, Date from_date, Date to_date) {
        this.emp_no = emp_no;
        this.dept_no = dept_no;
        this.from_date = from_date;
        this.to_date = to_date;
    }

    public Integer getEmp_no() {
        return emp_no;
    }

    public void setEmp_no(Integer emp_no) {
        this.emp_no = emp_no;
    }

    public String getDept_no() {
        return dept_no;
    }

    public void setDept_no(String dept_no) {
        this.dept_no = dept_no;
    }

    public Date getFrom_date() {
        return from_date;
    }

    public void setFrom_date(Date from_date) {
        this.from_date = from_date;
    }

    public Date getTo_date() {
        return to_date;
    }

    public void setTo_date(Date to_date) {
        this.to_date = to_date;
    }

    @Override
    public String toString() {
        return "DeptEmp{" +
                "emp_no=" + emp_no +
                ", dept_no='" + dept_no + '\'' +
                ", from_date=" + from_date +
                ", to_date=" + to_date +
                '}';
    }
}
