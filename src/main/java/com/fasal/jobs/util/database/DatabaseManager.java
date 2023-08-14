package com.fasal.jobs.util.database;

import java.sql.Connection;
import java.sql.SQLException;

public interface DatabaseManager {

	public Connection getConnection() throws ClassNotFoundException, SQLException;

}
