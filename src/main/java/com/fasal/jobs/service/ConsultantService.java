package com.fasal.jobs.service;

import java.sql.SQLException;
import java.util.*;

import com.fasal.jobs.dao.ConsultantAvailabilityManager;
import com.fasal.jobs.dao.ConsultantAvailabilityManagerImpl;
import com.fasal.jobs.dao.ConsultantManager;
import com.fasal.jobs.dao.ConsultantManagerImpl;
import com.fasal.jobs.enums.RecordTempState;
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

    result = createConsultantAvailabilities(consultant.getAvailability());
    return result;
  }

  public boolean update(Consultant consultant) throws ClassNotFoundException, SQLException {
    boolean result;
    boolean isValidEmail = Validator.getValidator().checkEmailValidity(consultant.getEmail());
    if (!isValidEmail) return false;

    result = getConsultantManager().update(consultant);
    if (!result) return false;

    Map<RecordTempState, List<ConsultantAvailability>> separatedConsultantAvailabilities;
    separatedConsultantAvailabilities = separateConsultantAvailabilities(consultant.getAvailability());

    result = updateAlteredConsultantAvailabilities(separatedConsultantAvailabilities.get(RecordTempState.UPDATED));
    if (!result) return false;

    result = deleteRemovedConsultantAvailabilities(separatedConsultantAvailabilities.get(RecordTempState.DELETED));
    if (!result) return false;

    result = createConsultantAvailabilities(separatedConsultantAvailabilities.get(RecordTempState.CREATED));

    return result;
  }

  public boolean delete(String id) throws ClassNotFoundException, SQLException {
    return getConsultantManager().delete(id);
  }

  public Consultant findUnique(String id) throws ClassNotFoundException, SQLException {
    Consultant consultant = getConsultantManager().findUnique(id);
    List<ConsultantAvailability> consultantAvailabilities = getConsultantAvailabilityManager().findMany(consultant.getId());
    consultant.setAvailability(consultantAvailabilities);

    return consultant;
  }

  public List<Consultant> findMany() throws ClassNotFoundException, SQLException {
    List<Consultant> consultants = getConsultantManager().findMany();

    for (Consultant consultant : consultants) {
      List<ConsultantAvailability> consultantAvailabilities = getConsultantAvailabilityManager().findMany(consultant.getId());
      consultant.setAvailability(consultantAvailabilities);
    }

    return consultants;
  }

  public String getMostAppointedConsultantId(int month, int year) throws ClassNotFoundException, SQLException {
    return getConsultantManager().getMostAppointedConsultantId(month, year);
  }

  public boolean createConsultantAvailabilities(List<ConsultantAvailability> consultantAvailabilities) throws SQLException, ClassNotFoundException {
    boolean result = true;

    for (ConsultantAvailability consultantAvailability : consultantAvailabilities) {
      String id = UUID.randomUUID().toString();
      consultantAvailability.setId(id);
      result = getConsultantAvailabilityManager().create(consultantAvailability);
      if (!result) break;
    }

    return result;
  }

  public boolean updateAlteredConsultantAvailabilities(List<ConsultantAvailability> consultantAvailabilities) throws SQLException, ClassNotFoundException {
    boolean result = true;

    for (ConsultantAvailability consultantAvailability : consultantAvailabilities) {
      result = getConsultantAvailabilityManager().update(consultantAvailability);
      if (!result) break;
    }

    return result;
  }

  public boolean deleteRemovedConsultantAvailabilities(List<ConsultantAvailability> consultantAvailabilities) throws SQLException, ClassNotFoundException {
    boolean result = true;

    for (ConsultantAvailability consultantAvailability : consultantAvailabilities) {
      result = getConsultantAvailabilityManager().delete(consultantAvailability.getId());
      if (!result) break;
    }

    return result;
  }

  public Map<RecordTempState, List<ConsultantAvailability>> separateConsultantAvailabilities(List<ConsultantAvailability> consultantAvailabilities) throws SQLException, ClassNotFoundException {
    Map<RecordTempState, List<ConsultantAvailability>> separatedAvailabilitiesMap = new HashMap<>();

    List<ConsultantAvailability> currentConsultantAvailabilities;
    currentConsultantAvailabilities = getConsultantAvailabilityManager().findMany(consultantAvailabilities.get(0).getConsultantId());

    List<ConsultantAvailability> modifiedAvailabilities = new ArrayList<>();
    List<ConsultantAvailability> removedAvailabilities = new ArrayList<>();
    List<ConsultantAvailability> addedAvailabilities = new ArrayList<>();

    for (ConsultantAvailability availability : consultantAvailabilities) {
      boolean isUpdated = false;

      for (ConsultantAvailability currentAvailability : currentConsultantAvailabilities) {
        if (availability.getId() == null || availability.getId().isEmpty()) break;

        isUpdated = availability.getId().equals(currentAvailability.getId());
        if (isUpdated) break;
      }

      if (isUpdated)
        modifiedAvailabilities.add(availability);
      else if (availability.getId() == null || availability.getId().isEmpty())
        addedAvailabilities.add(availability);
    }

    for (ConsultantAvailability currentAvailability : currentConsultantAvailabilities) {
      boolean isDeleted = false;
      for (ConsultantAvailability availability : consultantAvailabilities) {
        if (availability.getId() == null) break;

        isDeleted = !currentAvailability.getId().equals(availability.getId());

        if (!isDeleted) break;
      }

      if (isDeleted)
        removedAvailabilities.add(currentAvailability);
    }

    separatedAvailabilitiesMap.put(RecordTempState.UPDATED, modifiedAvailabilities);
    separatedAvailabilitiesMap.put(RecordTempState.DELETED, removedAvailabilities);
    separatedAvailabilitiesMap.put(RecordTempState.CREATED, addedAvailabilities);

    return separatedAvailabilitiesMap;
  }


}
