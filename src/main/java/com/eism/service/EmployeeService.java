package com.eism.service;

import com.eism.dao.EmployeeDao;
import com.eism.model.Employee;
import java.sql.SQLException;
import java.util.List;

public class EmployeeService {
    private final EmployeeDao dao;
    public EmployeeService(EmployeeDao dao) { this.dao = dao; }
    public List<Employee> search(String text) throws SQLException { return dao.search(text); }
    public void add(Employee employee) throws SQLException { dao.add(employee); }
    public boolean update(Employee employee) throws SQLException { return dao.update(employee); }
    public boolean delete(int id) throws SQLException { return dao.delete(id); }
}
