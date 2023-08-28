package com.fasal.jobs.util.validation;

import java.util.regex.Pattern;

public class Validator {
  private static Validator validator;
    private Pattern emailPattern = Pattern.compile("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$",
      Pattern.CASE_INSENSITIVE);

  public static synchronized Validator getValidator() {
    if (validator == null)
      validator = new Validator();
    return validator;
  }

  public boolean checkEmailValidity(String email) {
    boolean isValidEmail = (email != null && emailPattern.matcher(email).matches());
    return isValidEmail;
  }
}
