package com.eism.dao;

import com.eism.model.Employee;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao {
    private final Connection connection;

    // Creates the employee database object.
    public EmployeeDao(Connection connection) {
        this.connection = connection;
    }

    // Finds employees by name, email, or department.
    public List<Employee> search(String search) throws SQLException {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT * FROM employees WHERE first_name LIKE ? OR last_name LIKE ? "
                + "OR email LIKE ? OR department LIKE ? ORDER BY id";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            String value = "%" + search + "%";
            for (int i = 1; i <= 4; i++) {
                statement.setString(i, value);
            }
            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    employees.add(fromResult(result));
                }
            }
        }
        return employees;
    }

    // Adds a new employee to MySQL.
    public void add(Employee employee) throws SQLException {
        String sql = "INSERT INTO employees (first_name, last_name, email, phone, department, position, salary, hire_date) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        save(sql, employee, false);
    }

    // Updates an existing employee.
    public boolean update(Employee employee) throws SQLException {
        String sql = "UPDATE employees SET first_name=?, last_name=?, email=?, phone=?, "
                + "department=?, position=?, salary=?, hire_date=? WHERE id=?";
        return save(sql, employee, true) > 0;
    }

    // Deletes an employee by ID.
    public boolean delete(int id) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM employees WHERE id=?")) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        }
    }

    // Saves employee data for an insert or update.
    private int save(String sql, Employee employee, boolean update) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, employee.getFirstName());
            statement.setString(2, employee.getLastName());
            statement.setString(3, employee.getEmail());
            statement.setString(4, employee.getPhone());
            statement.setString(5, employee.getDepartment());
            statement.setString(6, employee.getPosition());
            statement.setBigDecimal(7, employee.getSalary());
            statement.setDate(8, Date.valueOf(employee.getHireDate()));
            if (update) {
                statement.setInt(9, employee.getId());
            }
            return statement.executeUpdate();
        }
    }

    // Converts a database row into an Employee object.
    private Employee fromResult(ResultSet result) throws SQLException {
        Employee employee = new Employee();
        employee.setId(result.getInt("id"));
        employee.setFirstName(result.getString("first_name"));
        employee.setLastName(result.getString("last_name"));
        employee.setEmail(result.getString("email"));
        employee.setPhone(result.getString("phone"));
        employee.setDepartment(result.getString("department"));
        employee.setPosition(result.getString("position"));
        employee.setSalary(result.getBigDecimal("salary"));
        employee.setHireDate(result.getDate("hire_date").toLocalDate());
        return employee;
    }
}
