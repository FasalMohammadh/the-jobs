package com.fasal.jobs.service;

import com.fasal.jobs.dao.EmployeeManagerImpl;
import com.fasal.jobs.dao.EmployeeManager;
import com.fasal.jobs.model.Employee;
import com.fasal.jobs.util.helper.Helper;
import com.fasal.jobs.util.validation.Validator;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpSession;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class AuthenticationService {
  private static AuthenticationService authenticationService;

  public static synchronized AuthenticationService getService() {
    if (authenticationService == null)
      authenticationService = new AuthenticationService();

    return authenticationService;
  }

  private Helper getHelper() {
    return Helper.getHelper();
  }

  private EmployeeManager getEmployeeManager() {
    return new EmployeeManagerImpl();
  }

  public boolean login(String email, String password, HttpSession session) throws SQLException, ClassNotFoundException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
    boolean isValidEmail = Validator.getValidator().checkEmailValidity(email);
    if (!isValidEmail) return false;

    Employee employee = getEmployeeManager().findUnique(email);
    String encryptedPassword = getHelper().toEncrypted(password);
    if (!(employee.getPassword().equals(encryptedPassword)))
      return false;

    session.setAttribute("User", employee);
    return true;
  }

  public boolean logout(HttpSession session) {
    session.removeAttribute("User");
    return true;
  }
}
