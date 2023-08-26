package com.fasal.jobs.service;

import java.sql.SQLException;
import java.util.List;

import com.fasal.jobs.dao.ConsultantManager;
import com.fasal.jobs.dao.ConsultantManagerImpl;
import com.fasal.jobs.model.Consultant;
import com.fasal.jobs.util.validation.Validator;

public class ConsultantService {
  private static ConsultantService consultantService;

  public static synchronized ConsultantService getService() {
    if (consultantService == null)
      consultantService = new ConsultantService();

    return consultantService;
  }

  private ConsultantManager getConsultantManager() {
    return new ConsultantManagerImpl();
  }

  public boolean create(Consultant consultant) throws ClassNotFoundException, SQLException {
    boolean isValidEmail = Validator.getValidator().checkEmailValidity(consultant.getEmail());
    if (isValidEmail)
      return getConsultantManager().create(consultant);

    return false;
  }

  public boolean update(Consultant consultant) throws ClassNotFoundException, SQLException {
    boolean isValidEmail = Validator.getValidator().checkEmailValidity(consultant.getEmail());
    if (isValidEmail)
      return getConsultantManager().update(consultant);

    return false;
  }

  public boolean delete(String id) throws ClassNotFoundException, SQLException {
    return getConsultantManager().delete(id);
  }

  public Consultant findUnique(String id) throws ClassNotFoundException, SQLException {
    return getConsultantManager().findUnique(id);
  }

  public List<Consultant> findMany() throws ClassNotFoundException, SQLException {
    return getConsultantManager().findMany();
  }
}
