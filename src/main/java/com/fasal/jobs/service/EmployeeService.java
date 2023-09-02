package com.fasal.jobs.service;

import com.fasal.jobs.dao.EmployeeManager;
import com.fasal.jobs.dao.EmployeeManageImpl;
import com.fasal.jobs.model.Employee;

import java.sql.SQLException;


public class EmployeeService {
  private static EmployeeService employeeService;

  public static synchronized EmployeeService getService() {
    if (employeeService == null)
      employeeService = new EmployeeService();

    return employeeService;
  }

  private EmployeeManager getEmployeeManager() {
    return new EmployeeManageImpl();
  }

//  public boolean create(Employee employee) throws ClassNotFoundException, SQLException {
//    return getEmployeeManager().create(employee);
//  }
//
//  public boolean update(Employee employee) throws ClassNotFoundException, SQLException {
//    return getEmployeeManager().update(employee);
//  }
//
//  public boolean delete(String id) throws ClassNotFoundException, SQLException {
//    return getEmployeeManager().delete(id);
//  }

  public Employee findUnique(String id) throws ClassNotFoundException, SQLException {
    return getEmployeeManager().findUnique(id);
  }

//  public List<Employee> findMany() throws ClassNotFoundException, SQLException {
//    return getEmployeeManager().findMany();
//  }
}
