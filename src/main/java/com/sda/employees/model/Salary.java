package com.sda.employees.model;

import java.sql.Date;

public class Salary {
    private Integer emp_no;
    private Integer salary;
    private Date from_date;
    private Date to_date;

    public Salary(Integer emp_no, Integer salary, Date from_date, Date to_date) {
        this.emp_no = emp_no;
        this.salary = salary;
        this.from_date = from_date;
        this.to_date = to_date;
    }

    public Integer getEmp_no() {
        return emp_no;
    }

    public void setEmp_no(Integer emp_no) {
        this.emp_no = emp_no;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
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
        return "Salary{" +
                "emp_no=" + emp_no +
                ", salary=" + salary +
                ", from_date=" + from_date +
                ", to_date=" + to_date +
                '}';
    }
}
