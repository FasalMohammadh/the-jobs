package com.fasal.jobs.service;

import com.fasal.jobs.dao.EmployeeManager;
import com.fasal.jobs.dao.EmployeeManagerImpl;
import com.fasal.jobs.model.Employee;
import com.fasal.jobs.util.helper.Helper;
import com.fasal.jobs.util.validation.Validator;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;


public class EmployeeService {
  private static EmployeeService employeeService;

  private EmployeeService(){}

  public static synchronized EmployeeService getService() {
    if (employeeService == null)
      employeeService = new EmployeeService();

    return employeeService;
  }

  private EmployeeManager getEmployeeManager() {
    return new EmployeeManagerImpl();
  }

  public Employee findUnique(String id) throws ClassNotFoundException, SQLException {
    return getEmployeeManager().findUnique(id);
  }

  public boolean login(String email, String password) throws ClassNotFoundException, SQLException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
    boolean isValidEmail = Validator.getValidator().checkEmailValidity(email);
    if (!isValidEmail) return false;

    Employee employee = getEmployeeManager().findUnique(email);

    if (employee == null) return false;

    String encryptedPassword = Helper.getHelper().toEncrypted(password);
    return employee.getPassword().equals(encryptedPassword);
  }
}
