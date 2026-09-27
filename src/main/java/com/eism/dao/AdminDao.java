package com.eism.dao;

import com.eism.utility.PasswordUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDao {
    private final Connection connection;
    private final PasswordUtil passwordUtil = new PasswordUtil();

    public AdminDao(Connection connection) { this.connection = connection; }

    public boolean isEmpty() throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("SELECT COUNT(*) FROM admins");
             ResultSet result = statement.executeQuery()) {
            result.next();
            return result.getInt(1) == 0;
        }
    }

    public void create(String username, String password) throws SQLException {
        String salt = passwordUtil.createSalt();
        String sql = "INSERT INTO admins (username, password_hash, password_salt) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            statement.setString(2, passwordUtil.hash(password, salt));
            statement.setString(3, salt);
            statement.executeUpdate();
        }
    }

    public boolean authenticate(String username, String password) throws SQLException {
        String sql = "SELECT password_hash, password_salt FROM admins WHERE username = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            try (ResultSet result = statement.executeQuery()) {
                return result.next() && passwordUtil.matches(password,
                        result.getString("password_hash"), result.getString("password_salt"));
            }
        }
    }
}
