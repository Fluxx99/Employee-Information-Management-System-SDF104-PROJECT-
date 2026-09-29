package com.eism.view;

import com.eism.model.Employee;
import java.io.Console;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class ConsoleView {
    private final Scanner scanner = new Scanner(System.in);

    // Reads normal text input.
    public String read(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    // Reads a password and hides it when possible.
    public String readPassword(String prompt) {
        Console console = System.console();
        return console == null ? read(prompt) : new String(console.readPassword(prompt));
    }

    // Prints a message.
    public void message(String text) {
        System.out.println(text);
    }

    // Prints the program title.
    public void title() {
        message("\n=== Employee Information Management System ===");
    }

    // Asks for all employee details.
    public Employee readEmployee() {
        Employee employee = new Employee();
        employee.setFirstName(read("First name: "));
        employee.setLastName(read("Last name: "));
        employee.setEmail(read("Email: "));
        employee.setPhone(read("Phone: "));
        employee.setDepartment(read("Department: "));
        employee.setPosition(read("Position: "));
        employee.setSalary(readSalary());
        employee.setHireDate(readDate());
        return employee;
    }

    // Reads and checks the salary.
    private BigDecimal readSalary() {
        while (true) {
            try {
                BigDecimal salary = new BigDecimal(read("Salary: "));
                if (salary.signum() < 0) {
                    throw new NumberFormatException();
                }
                return salary;
            } catch (NumberFormatException e) {
                message("Invalid salary. Please try again.");
            }
        }
    }

    // Reads and checks the hire date.
    private LocalDate readDate() {
        while (true) {
            try {
                return LocalDate.parse(read("Hire date (YYYY-MM-DD): "));
            } catch (DateTimeParseException e) {
                message("Invalid date. Use YYYY-MM-DD and try again.");
            }
        }
    }

    // Displays employee records.
    public void showEmployees(List<Employee> employees) {
        if (employees.isEmpty()) {
            message("No employees found.");
            return;
        }
        for (Employee employee : employees) {
            System.out.printf("%d | %s %s | %s | %s | %s | %s | %s | %s%n",
                    employee.getId(), employee.getFirstName(), employee.getLastName(),
                    employee.getEmail(), employee.getPhone(), employee.getDepartment(),
                    employee.getPosition(), employee.getSalary(), employee.getHireDate());
        }
    }

    // Displays the employee menu.
    public void menu() {
        message("\n1. View employees\n2. Search employees\n3. Add employee\n"
                + "4. Update employee\n5. Delete employee\n6. Exit");
    }
}
