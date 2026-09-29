package com.eism.controller;

import com.eism.model.Employee;
import com.eism.service.EmployeeService;
import com.eism.view.ConsoleView;
import java.sql.SQLException;

public class EmployeeController {
    private final EmployeeService service;
    private final ConsoleView view;

    // Creates the employee controller.
    public EmployeeController(EmployeeService service, ConsoleView view) {
        this.service = service;
        this.view = view;
    }

    // Shows the employee menu and handles the selected option.
    public void run() {
        while (true) {
            view.menu();
            try {
                switch (view.read("Choose: ")) {
                    case "1" -> view.showEmployees(service.search(""));
                    case "2" -> view.showEmployees(service.search(view.read("Search: ")));
                    case "3" -> {
                        service.add(view.readEmployee());
                        view.message("Employee added.");
                    }
                    case "4" -> update();
                    case "5" -> delete();
                    case "6" -> {
                        return;
                    }
                    default -> view.message("Invalid option.");
                }
            } catch (SQLException | IllegalArgumentException e) {
                view.message("Could not complete that action. Check your input.");
            }
        }
    }

    // Gets new employee details and updates the employee.
    private void update() throws SQLException {
        int id = Integer.parseInt(view.read("Employee ID: "));
        Employee employee = view.readEmployee();
        employee.setId(id);
        view.message(service.update(employee) ? "Employee updated." : "Employee not found.");
    }

    // Deletes an employee using the employee ID.
    private void delete() throws SQLException {
        int id = Integer.parseInt(view.read("Employee ID: "));
        view.message(service.delete(id) ? "Employee deleted." : "Employee not found.");
    }
}
