package com.eism.service;

import com.eism.dao.AdminDao;
import com.eism.view.ConsoleView;
import java.sql.SQLException;

public class AuthService {
    private final AdminDao adminDao;
    private final ConsoleView view;

    // Creates the authentication service.
    public AuthService(AdminDao adminDao, ConsoleView view) { this.adminDao = adminDao; this.view = view; }

    // Creates the first admin if the admins table is empty.
    public void createFirstAdminIfNeeded() throws SQLException {
        if (adminDao.isEmpty()) {
            view.message("No admin account exists yet.");
            adminDao.create(view.read("Create admin username: "), view.readPassword("Create admin password: "));
            view.message("Admin account created.\n");
        }
    }

    // Checks the login details.
    public boolean authenticate(String username, String password) throws SQLException {
        return adminDao.authenticate(username, password);
    }
}
