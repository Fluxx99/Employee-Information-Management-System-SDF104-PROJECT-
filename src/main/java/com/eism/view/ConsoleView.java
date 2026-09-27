package com.eism.view;

import com.eism.model.Employee;
import java.io.Console;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class ConsoleView {
    private final Scanner scanner = new Scanner(System.in);
    public String read(String prompt) { System.out.print(prompt); return scanner.nextLine().trim(); }
    public String readPassword(String prompt) {
        Console console = System.console();
        return console == null ? read(prompt) : new String(console.readPassword(prompt));
    }
    public void message(String text) { System.out.println(text); }
    public void title() { message("\n=== Employee Information Management System ==="); }
    public Employee readEmployee() {
        Employee e = new Employee();
        e.setFirstName(read("First name: ")); e.setLastName(read("Last name: "));
        e.setEmail(read("Email: ")); e.setPhone(read("Phone: "));
        e.setDepartment(read("Department: ")); e.setPosition(read("Position: "));
        e.setSalary(readSalary());
        e.setHireDate(readDate());
        return e;
    }

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

    private LocalDate readDate() {
        while (true) {
            try {
                return LocalDate.parse(read("Hire date (YYYY-MM-DD): "));
            } catch (java.time.format.DateTimeParseException e) {
                message("Invalid date. Use YYYY-MM-DD and try again.");
            }
        }
    }
    public void showEmployees(List<Employee> employees) {
        if (employees.isEmpty()) { message("No employees found."); return; }
        for (Employee e : employees) {
            System.out.printf("%d | %s %s | %s | %s | %s | %s | %s | %s%n",
                    e.getId(), e.getFirstName(), e.getLastName(), e.getEmail(), e.getPhone(),
                    e.getDepartment(), e.getPosition(), e.getSalary(), e.getHireDate());
        }
    }
    public void menu() {
        message("\n1. View employees\n2. Search employees\n3. Add employee\n"
                + "4. Update employee\n5. Delete employee\n6. Exit");
    }
}
