package com.fasal.jobs.util.database;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyLoader {

  private String mysqlDatabaseUrl;
  private String mysqlDatabaseUsername;
  private String mysqlDatabasePassword;


  public PropertyLoader() {
    try {
      Properties configuration = new Properties();
      InputStream inputStream = PropertyLoader.class
              .getClassLoader()
              .getResourceAsStream("application.properties");
      configuration.load(inputStream);

      mysqlDatabaseUrl = configuration.getProperty("MYSQL_DATABASE_URL");
      mysqlDatabaseUsername = configuration.getProperty("MYSQL_DATABASE_USER");
      mysqlDatabasePassword = configuration.getProperty("MYSQL_DATABASE_PASSWORD");

      inputStream.close();
    } catch (IOException e) {
      System.err.println(e.getMessage());
    }
  }



  public String getMysqlDatabaseUrl() {
    return mysqlDatabaseUrl;
  }

  public String getMysqlDatabaseUsername() {
    return mysqlDatabaseUsername;
  }

  public String getMysqlDatabasePassword() {
    return mysqlDatabasePassword;
  }
}
