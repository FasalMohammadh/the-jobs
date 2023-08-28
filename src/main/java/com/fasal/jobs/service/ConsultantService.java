package com.fasal.jobs.service;

import java.sql.SQLException;
import java.util.List;

import com.fasal.jobs.dao.ConsultantAvailabilityManager;
import com.fasal.jobs.dao.ConsultantAvailabilityManagerImpl;
import com.fasal.jobs.dao.ConsultantManager;
import com.fasal.jobs.dao.ConsultantManagerImpl;
import com.fasal.jobs.model.Consultant;
import com.fasal.jobs.model.ConsultantAvailability;
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

  private ConsultantAvailabilityManager getConsultantAvailabilityManager() {
    return new ConsultantAvailabilityManagerImpl();
  }

  public boolean create(Consultant consultant) throws ClassNotFoundException, SQLException {
    boolean result;
    boolean isValidEmail = Validator.getValidator().checkEmailValidity(consultant.getEmail());
    if (!isValidEmail) return false;

    result = getConsultantManager().create(consultant);
    if (!result) return result;

    result = createConsultantAvailabilities(consultant);
    return result;
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

  public boolean createConsultantAvailabilities(Consultant consultant) throws SQLException, ClassNotFoundException {
    List<ConsultantAvailability> consultantAvailabilities = consultant.getAvailability();
    boolean result = true;

    for (int i = 0; i < consultantAvailabilities.size(); i++) {
      result = getConsultantAvailabilityManager().create(consultantAvailabilities.get(i), consultant.getId());
      if (!result) break;
    }

    return result;
  }
}
