package com.fasal.jobs.dao;

import com.fasal.jobs.model.Employee;

import java.sql.SQLException;

public interface EmployeeManager {

  public Employee findUnique(String email) throws SQLException, ClassNotFoundException;
}
