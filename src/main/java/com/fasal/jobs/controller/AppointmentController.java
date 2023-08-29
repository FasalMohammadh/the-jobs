package com.fasal.jobs.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasal.jobs.enums.ActionType;
import com.fasal.jobs.enums.AppointmentStatus;
import com.fasal.jobs.model.Appointment;
import com.fasal.jobs.service.AppointmentService;

public class AppointmentController extends HttpServlet {

  private AppointmentService getAppointmentService() {
    return AppointmentService.getService();
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    getAppointments(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    ActionType actionType = ActionType.valueOf(request.getParameter("actionType"));
    switch (actionType) {
      case CREATE:
        createAppointment(request, response);
        break;
      case UPDATE:
        updateAppointment(request, response);
        break;
      case DELETE:
        deleteAppointment(request, response);
        break;
    }
  }

  private void createAppointment(HttpServletRequest request, HttpServletResponse response) {
    try {
      String id = UUID.randomUUID().toString();

      String date = request.getParameter("date");
      String time = request.getParameter("time");
      String consultantId = request.getParameter("consultantId");
      String jobSeekerId = request.getParameter("jobSeekerId");
      String country = request.getParameter("country");
      String job = request.getParameter("job");

      Appointment appointment = new Appointment(id, null, consultantId, jobSeekerId, country, job, null,
              AppointmentStatus.CREATED);

      boolean isCreated = getAppointmentService().create(appointment);
      if (isCreated) {

      } else {

      }
    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
    }
  }

  private void updateAppointment(HttpServletRequest request, HttpServletResponse response) {
    try {
      String id = UUID.randomUUID().toString();
      String date = request.getParameter("date");
      String time = request.getParameter("time");
      String consultantId = request.getParameter("consultantId");
      String jobSeekerId = request.getParameter("jobSeekerId");
      String country = request.getParameter("country");
      String job = request.getParameter("job");

      Appointment appointment = new Appointment(id, null, consultantId, jobSeekerId, country, job, null,
              AppointmentStatus.CREATED);

      boolean isUpdated = getAppointmentService().update(appointment);
      if (isUpdated) {

      } else {

      }
    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
    }
  }

  private void deleteAppointment(HttpServletRequest request, HttpServletResponse response) {
    try {
      String id = request.getParameter("id");
      boolean isDeleted = getAppointmentService().delete(id);

      if (isDeleted) {

      } else {

      }
    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
    }
  }

  private void getAppointments(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    try {
      List<Appointment> appointments;
      appointments = getAppointmentService().findMany();
      request.setAttribute("jobSeekers", appointments);
      RequestDispatcher requestDispatcher = request.getRequestDispatcher("appointment.jsp");
      requestDispatcher.forward(request, response);
    } catch (ClassNotFoundException | SQLException exception) {
      exception.printStackTrace();
      System.out.println(exception.getMessage());
    }
  }
}
