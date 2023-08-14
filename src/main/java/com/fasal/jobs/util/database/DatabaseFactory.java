package com.fasal.jobs.util.database;

import com.fasal.jobs.enums.DatabaseType;

public class DatabaseFactory {
	public DatabaseManager getDatabase(DatabaseType type) {
		switch(type) {
		case MYSQL:
			return new MySqlDatabaseManager();
			default: return null;
		}
	}
}
