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

  public Employee findUnique(String id) throws ClassNotFoundException, SQLException {
    return getEmployeeManager().findUnique(id);
  }

}
