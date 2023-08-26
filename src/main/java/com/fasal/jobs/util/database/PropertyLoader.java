package com.fasal.jobs.util.database;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyLoader {

  private static String mysqlDatabaseUrl;
  private static String mysqlDatabaseUsername;
  private static String mysqlDatabasePassword;

  private PropertyLoader() {
    try {
      Properties configuration = new Properties();
      InputStream inputStream = PropertyLoader.class
          .getClassLoader()
          .getResourceAsStream("application.properties");
      configuration.load(inputStream);
      inputStream.close();
    } catch (IOException e) {
      System.err.println(e.getMessage());
    }
  }

  public static String getMysqlDatabaseUrl() {
    return mysqlDatabaseUrl;
  }

  public static String getMysqlDatabaseUsername() {
    return mysqlDatabaseUsername;
  }

  public static String getMysqlDatabasePassword() {
    return mysqlDatabasePassword;
  }

}
