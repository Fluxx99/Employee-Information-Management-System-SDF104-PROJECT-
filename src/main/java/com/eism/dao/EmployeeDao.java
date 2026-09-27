package com.eism.dao;

import com.eism.model.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao {
    private final Connection connection;
    public EmployeeDao(Connection connection) { this.connection = connection; }

    public List<Employee> search(String search) throws SQLException {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT * FROM employees WHERE first_name LIKE ? OR last_name LIKE ? "
                + "OR email LIKE ? OR department LIKE ? ORDER BY id";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            String value = "%" + search + "%";
            for (int i = 1; i <= 4; i++) statement.setString(i, value);
            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) employees.add(fromResult(result));
            }
        }
        return employees;
    }

    public void add(Employee employee) throws SQLException {
        String sql = "INSERT INTO employees (first_name, last_name, email, phone, department, position, salary, hire_date) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        save(sql, employee, false);
    }

    public boolean update(Employee employee) throws SQLException {
        String sql = "UPDATE employees SET first_name=?, last_name=?, email=?, phone=?, "
                + "department=?, position=?, salary=?, hire_date=? WHERE id=?";
        return save(sql, employee, true) > 0;
    }

    public boolean delete(int id) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM employees WHERE id=?")) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        }
    }

    private int save(String sql, Employee e, boolean update) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, e.getFirstName()); statement.setString(2, e.getLastName());
            statement.setString(3, e.getEmail()); statement.setString(4, e.getPhone());
            statement.setString(5, e.getDepartment()); statement.setString(6, e.getPosition());
            statement.setBigDecimal(7, e.getSalary()); statement.setDate(8, Date.valueOf(e.getHireDate()));
            if (update) statement.setInt(9, e.getId());
            return statement.executeUpdate();
        }
    }

    private Employee fromResult(ResultSet result) throws SQLException {
        Employee e = new Employee();
        e.setId(result.getInt("id")); e.setFirstName(result.getString("first_name"));
        e.setLastName(result.getString("last_name")); e.setEmail(result.getString("email"));
        e.setPhone(result.getString("phone")); e.setDepartment(result.getString("department"));
        e.setPosition(result.getString("position")); e.setSalary(result.getBigDecimal("salary"));
        e.setHireDate(result.getDate("hire_date").toLocalDate());
        return e;
    }
}
