package com.fasal.jobs.dao;

import com.fasal.jobs.model.Consultant;
import com.fasal.jobs.model.ConsultantAvailability;

import java.sql.SQLException;
import java.util.List;

public interface ConsultantAvailabilityManager {
  public boolean create(ConsultantAvailability consultantAvailability, String consultant_id) throws ClassNotFoundException, SQLException;

  public boolean update(ConsultantAvailability consultantAvailability) throws ClassNotFoundException, SQLException;

  public boolean delete(String id) throws ClassNotFoundException, SQLException;

}
