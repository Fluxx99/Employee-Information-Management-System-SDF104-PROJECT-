package com.eism;

import com.eism.controller.EmployeeController;
import com.eism.controller.LoginController;
import com.eism.dao.AdminDao;
import com.eism.dao.EmployeeDao;
import com.eism.exception.EismException;
import com.eism.service.AuthService;
import com.eism.service.EmployeeService;
import com.eism.utility.DatabaseConnection;
import com.eism.view.ConsoleView;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try (Connection connection = DatabaseConnection.open()) {
            ConsoleView view = new ConsoleView();
            AuthService authService = new AuthService(new AdminDao(connection), view);
            authService.createFirstAdminIfNeeded();

            if (new LoginController(authService, view).login()) {
                new EmployeeController(new EmployeeService(new EmployeeDao(connection)), view).run();
            }
        } catch (SQLException | EismException e) {
            System.out.println("Could not connect to MySQL.");
            System.out.println("Check src/main/resources/db.properties and make sure MySQL is running.");
        }
    }
}
