package com.fasal.jobs.dao;

import java.sql.SQLException;
import java.util.List;

import com.fasal.jobs.model.Consultant;

public interface ConsultantManager {
  public boolean create(Consultant consultant) throws ClassNotFoundException, SQLException;

  public boolean update(Consultant consultant) throws ClassNotFoundException, SQLException;

  public boolean delete(String id) throws ClassNotFoundException, SQLException;

  public Consultant findUnique(String id) throws ClassNotFoundException, SQLException;

  public List<Consultant> findMany() throws ClassNotFoundException, SQLException;

  public String getMostAppointedConsultantId(int month, int year) throws ClassNotFoundException, SQLException;

}
