package com.fasal.jobs.dao;

import com.fasal.jobs.enums.DatabaseType;
import com.fasal.jobs.model.Employee;
import com.fasal.jobs.util.database.DatabaseFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface EmployeeManager {

  public Employee findUnique(String email) throws SQLException, ClassNotFoundException;

  public Connection getMysqlConnection() throws SQLException, ClassNotFoundException;


}
