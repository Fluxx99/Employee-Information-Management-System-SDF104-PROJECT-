package com.eism.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Employee {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String department;
    private String position;
    private BigDecimal salary;
    private LocalDate hireDate;

    // Gets the employee ID.
    public int getId() { return id; }
    // Sets the employee ID.
    public void setId(int id) { this.id = id; }
    // Gets the first name.
    public String getFirstName() { return firstName; }
    // Sets the first name.
    public void setFirstName(String value) { firstName = value; }
    // Gets the last name.
    public String getLastName() { return lastName; }
    // Sets the last name.
    public void setLastName(String value) { lastName = value; }
    // Gets the email.
    public String getEmail() { return email; }
    // Sets the email.
    public void setEmail(String value) { email = value; }
    // Gets the phone number.
    public String getPhone() { return phone; }
    // Sets the phone number.
    public void setPhone(String value) { phone = value; }
    // Gets the department.
    public String getDepartment() { return department; }
    // Sets the department.
    public void setDepartment(String value) { department = value; }
    // Gets the job position.
    public String getPosition() { return position; }
    // Sets the job position.
    public void setPosition(String value) { position = value; }
    // Gets the salary.
    public BigDecimal getSalary() { return salary; }
    // Sets the salary.
    public void setSalary(BigDecimal value) { salary = value; }
    // Gets the hire date.
    public LocalDate getHireDate() { return hireDate; }
    // Sets the hire date.
    public void setHireDate(LocalDate value) { hireDate = value; }
}
