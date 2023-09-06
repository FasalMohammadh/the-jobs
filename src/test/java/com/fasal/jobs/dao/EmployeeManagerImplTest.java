package com.fasal.jobs.dao;

import com.fasal.jobs.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

class EmployeeManagerImplTest {
  EmployeeManager employeeManager;
  Employee employee;
  EmployeeManager spyEmployeeManager;

  @BeforeEach
  void setUp() {
    employeeManager = new EmployeeManagerImpl();
    spyEmployeeManager = spy(employeeManager);
  }

  @Test
  @Disabled
  void findUnique() throws SQLException, ClassNotFoundException {
    Connection connection = mock(Connection.class);
    PreparedStatement statement = mock(PreparedStatement.class);
    ResultSet resultSet = mock(ResultSet.class);

    String email = "example@gmail.com";
    String id = UUID.randomUUID().toString();
    Employee expectedEmployee = new Employee(id, "test1", "test2", email, "test3", "test4", "test5");

    when(spyEmployeeManager.getMysqlConnection()).thenReturn(connection);
    when(connection.prepareStatement(anyString())).thenReturn(statement);
    when(statement.executeQuery()).thenReturn(resultSet);
    when(resultSet.next()).thenReturn(true);

    when(resultSet.getString("id")).thenReturn(id);
    when(resultSet.getString("email")).thenReturn(email);
    when(resultSet.getString("first_name")).thenReturn(expectedEmployee.getFirstName());
    when(resultSet.getString("last_name")).thenReturn(expectedEmployee.getLastName());
    when(resultSet.getString("password")).thenReturn(expectedEmployee.getPassword());
    when(resultSet.getString("role")).thenReturn(expectedEmployee.getRole());
    when(resultSet.getString("created_at")).thenReturn(expectedEmployee.getCreatedAt());

    Employee actualEmployee = employeeManager.findUnique(email);

    assertEquals(expectedEmployee, actualEmployee);

  }

}