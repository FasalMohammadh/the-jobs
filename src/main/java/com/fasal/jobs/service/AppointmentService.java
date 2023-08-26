package com.fasal.jobs.service;

import java.sql.SQLException;
import java.util.List;

import com.fasal.jobs.dao.AppointmentManager;
import com.fasal.jobs.dao.AppointmentManagerImpl;
import com.fasal.jobs.model.Appointment;

public class AppointmentService {
  private static AppointmentService appointmentService;

  public static synchronized AppointmentService getService() {
    if (appointmentService == null)
      appointmentService = new AppointmentService();

    return appointmentService;
  }

  private AppointmentManager getAppointmentManager() {
    return new AppointmentManagerImpl();
  }

  public boolean create(Appointment appointment) throws ClassNotFoundException, SQLException {
    return getAppointmentManager().create(appointment);
  }

  public boolean update(Appointment appointment) throws ClassNotFoundException, SQLException {
    return getAppointmentManager().update(appointment);
  }

  public boolean delete(String id) throws ClassNotFoundException, SQLException {
    return getAppointmentManager().delete(id);
  }

  public Appointment findUnique(String id) throws ClassNotFoundException, SQLException {
    return getAppointmentManager().findUnique(id);
  }

  public List<Appointment> findMany() throws ClassNotFoundException, SQLException {
    return getAppointmentManager().findMany();
  }
}
