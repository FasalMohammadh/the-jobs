package com.fasal.jobs.util.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlDatabaseManager implements DatabaseManager {

  @Override
  public Connection getConnection() throws ClassNotFoundException, SQLException {

    Class.forName("com.mysql.jdbc.Driver");

    PropertyLoader propertyLoader = new PropertyLoader();

    String mysqlDatabaseUrl = propertyLoader.getMysqlDatabaseUrl();
    String mysqlDatabaseUsername = propertyLoader.getMysqlDatabaseUsername();
    String mysqlDatabasePassword = propertyLoader.getMysqlDatabasePassword();

    return DriverManager.getConnection(mysqlDatabaseUrl, mysqlDatabaseUsername, mysqlDatabasePassword);

  }

}
