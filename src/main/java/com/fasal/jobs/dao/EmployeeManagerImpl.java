package com.fasal.jobs.dao;

import com.fasal.jobs.enums.DatabaseType;
import com.fasal.jobs.model.Employee;
import com.fasal.jobs.util.database.DatabaseFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeManagerImpl implements EmployeeManager {

  @Override
  public Connection getMysqlConnection() throws SQLException, ClassNotFoundException {
    return new DatabaseFactory().getDatabase(DatabaseType.MYSQL).getConnection();
  }

  @Override
  public Employee findUnique(String email) throws SQLException, ClassNotFoundException {
    Connection connection = getMysqlConnection();
    String query = "call FindEmployeeByEmail(?)";
    PreparedStatement findStatement = connection.prepareStatement(query);

    findStatement.setString(1, email);
    ResultSet result = findStatement.executeQuery();

    Employee employee = getEmployeeFromResultSet(result);

    result.close();
    findStatement.close();
    connection.close();

    return employee;
  }

  public Employee getEmployeeFromResultSet(ResultSet result) throws SQLException {
    Employee employee = null;
    if (result.next())
      employee = new Employee(
              result.getString("id"),
              result.getString("first_name"),
              result.getString("last_name"),
              result.getString("email"),
              result.getString("password"),
              result.getString("role"),
              result.getString("created_at")
      );

    return employee;
  }
}
