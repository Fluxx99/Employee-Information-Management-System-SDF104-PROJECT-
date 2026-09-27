package com.eism.utility;

import com.eism.exception.EismException;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public final class DatabaseConnection {
    private DatabaseConnection() { }

    public static Connection open() throws SQLException {
        Properties settings = new Properties();
        try (InputStream file = DatabaseConnection.class.getClassLoader().getResourceAsStream("db.properties")) {
            if (file == null) {
                throw new EismException("db.properties is missing", null);
            }
            settings.load(file);
        } catch (IOException e) {
            throw new EismException("Could not read db.properties.", e);
        }
        return DriverManager.getConnection(settings.getProperty("db.url"),
                settings.getProperty("db.username"), settings.getProperty("db.password"));
    }
}
