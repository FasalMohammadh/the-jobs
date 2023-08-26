package com.fasal.jobs.util.helper;

import java.sql.Date;
import java.time.LocalDate;

public class Helper {
  private static Helper helper;

  public static synchronized Helper getHelper() {
    if (helper == null)
      helper = new Helper();

    return helper;
  }

  public Date localDateToSqlDate(LocalDate date) {
    return Date.valueOf(date);
  }
}
