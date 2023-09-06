package com.fasal.jobs.service;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

import com.fasal.jobs.dao.JobSeekerManager;
import com.fasal.jobs.dao.JobSeekerManagerImpl;
import com.fasal.jobs.model.Employee;
import com.fasal.jobs.model.JobSeeker;
import com.fasal.jobs.util.helper.Helper;
import com.fasal.jobs.util.validation.Validator;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class JobSeekerService {
  private static JobSeekerService jobSeekerService;

  public static synchronized JobSeekerService getService() {
    if (jobSeekerService == null)
      jobSeekerService = new JobSeekerService();

    return jobSeekerService;
  }

  private Helper getHelper() {
    return Helper.getHelper();
  }

  private Validator getValidator() {
    return Validator.getValidator();
  }

  private JobSeekerManager getJobSeekerManager() {
    return new JobSeekerManagerImpl();
  }

  public boolean create(JobSeeker jobSeeker) throws ClassNotFoundException, SQLException {
    return getJobSeekerManager().create(jobSeeker);
  }

  public boolean update(JobSeeker jobSeeker) throws ClassNotFoundException, SQLException {
    return getJobSeekerManager().update(jobSeeker);
  }

  public boolean delete(String id) throws ClassNotFoundException, SQLException {
    return getJobSeekerManager().delete(id);
  }

  public JobSeeker findUnique(String id) throws ClassNotFoundException, SQLException {
    return getJobSeekerManager().findUnique(id);
  }

  public List<JobSeeker> findMany() throws ClassNotFoundException, SQLException {
    return getJobSeekerManager().findMany();
  }

  public boolean login(String email, String password) throws SQLException, ClassNotFoundException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
    boolean isValidEmail = Validator.getValidator().checkEmailValidity(email);
    if (!isValidEmail) return false;

    JobSeeker jobSeeker = getJobSeekerManager().findUniqueByEmail(email);

    if (jobSeeker == null) return false;

    String encryptedPassword = Helper.getHelper().toEncrypted(password);

    return jobSeeker.getPassword().equals(encryptedPassword);
  }

  public boolean register(JobSeeker jobSeeker) throws ClassNotFoundException, SQLException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
    if (validateJobSeeker(jobSeeker) == false) return false;

    String encryptedPassword = getHelper().toEncrypted(jobSeeker.getPassword());
    jobSeeker.setPassword(encryptedPassword);

    boolean isRegistered = getJobSeekerManager().register(jobSeeker);
    return isRegistered;
  }

  private boolean validateJobSeeker(JobSeeker jobSeeker) {
    return getValidator().checkEmailValidity(jobSeeker.getEmail());
  }

}
