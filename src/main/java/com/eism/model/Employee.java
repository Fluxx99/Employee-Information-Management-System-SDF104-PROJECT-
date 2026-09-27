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

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String value) { firstName = value; }
    public String getLastName() { return lastName; }
    public void setLastName(String value) { lastName = value; }
    public String getEmail() { return email; }
    public void setEmail(String value) { email = value; }
    public String getPhone() { return phone; }
    public void setPhone(String value) { phone = value; }
    public String getDepartment() { return department; }
    public void setDepartment(String value) { department = value; }
    public String getPosition() { return position; }
    public void setPosition(String value) { position = value; }
    public BigDecimal getSalary() { return salary; }
    public void setSalary(BigDecimal value) { salary = value; }
    public LocalDate getHireDate() { return hireDate; }
    public void setHireDate(LocalDate value) { hireDate = value; }
}
