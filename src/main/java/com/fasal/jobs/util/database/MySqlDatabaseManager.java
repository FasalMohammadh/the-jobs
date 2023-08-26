package com.fasal.jobs.util.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlDatabaseManager implements DatabaseManager {

	@Override
	public Connection getConnection() throws ClassNotFoundException, SQLException {

		Class.forName("com.mysql.jdbc.Driver");

		String mysqlDatabaseUrl = PropertyLoader.getMysqlDatabaseUrl();
		String mysqlDatabaseUsername = PropertyLoader.getMysqlDatabaseUsername();
		String mysqlDatabasePassword = PropertyLoader.getMysqlDatabasePassword();

		return DriverManager.getConnection(mysqlDatabaseUrl, mysqlDatabaseUsername, mysqlDatabasePassword);

	}

}
