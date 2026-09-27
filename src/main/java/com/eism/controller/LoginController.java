package com.eism.controller;

import com.eism.service.AuthService;
import com.eism.view.ConsoleView;
import java.sql.SQLException;

public class LoginController {
    private final AuthService auth;
    private final ConsoleView view;

    // Creates the login controller.
    public LoginController(AuthService auth, ConsoleView view) { this.auth = auth; this.view = view; }

    // Shows the login screen and checks the login details.
    public boolean login() throws SQLException {
        view.title();
        boolean valid = auth.authenticate(view.read("Username: "), view.readPassword("Password: "));
        view.message(valid ? "Login successful." : "Invalid username or password.");
        return valid;
    }
}
