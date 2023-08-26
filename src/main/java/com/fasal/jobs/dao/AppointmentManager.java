package com.fasal.jobs.dao;

import com.fasal.jobs.model.Appointment;

import java.sql.SQLException;
import java.util.List;

public interface AppointmentManager {

  public boolean create(Appointment appointment) throws ClassNotFoundException, SQLException;

  public boolean update(Appointment appointment) throws ClassNotFoundException, SQLException;

  public boolean delete(String appointmentId) throws ClassNotFoundException, SQLException;

  public Appointment findUnique(String appointmentId) throws ClassNotFoundException, SQLException;

  public List<Appointment> findMany() throws ClassNotFoundException, SQLException;

}
