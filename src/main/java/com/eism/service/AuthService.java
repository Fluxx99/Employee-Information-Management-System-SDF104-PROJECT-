package com.eism.service;

import com.eism.dao.AdminDao;
import com.eism.view.ConsoleView;
import java.sql.SQLException;

public class AuthService {
    private final AdminDao adminDao;
    private final ConsoleView view;
    public AuthService(AdminDao adminDao, ConsoleView view) { this.adminDao = adminDao; this.view = view; }

    public void createFirstAdminIfNeeded() throws SQLException {
        if (adminDao.isEmpty()) {
            view.message("No admin account exists yet.");
            adminDao.create(view.read("Create admin username: "), view.readPassword("Create admin password: "));
            view.message("Admin account created.\n");
        }
    }

    public boolean authenticate(String username, String password) throws SQLException {
        return adminDao.authenticate(username, password);
    }
}
