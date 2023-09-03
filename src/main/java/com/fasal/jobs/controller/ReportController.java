package com.fasal.jobs.controller;

import com.fasal.jobs.model.Appointment;
import com.fasal.jobs.model.Consultant;
import com.fasal.jobs.service.AppointmentService;
import com.fasal.jobs.service.ConsultantService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ReportController extends HttpServlet {

  String feedback;

  private AppointmentService getAppointmentService() {
    return new AppointmentService();
  }

  private ConsultantService getConsultantService() {
    return ConsultantService.getService();
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.getRequestDispatcher("report.jsp").forward(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    generateAppointmentReport(request, response);
  }

  public void generateAppointmentReport(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String yearMonth = request.getParameter("yearMonth");
    int year = Integer.parseInt(yearMonth.split("-")[0]);
    int month = Integer.parseInt(yearMonth.split("-")[1]);

    try {
      List<Appointment> appointments = getAppointmentService().findMany(month, year);
      String mostAppointedJob = getAppointmentService().getMostAppointedJob(month, year);
      String mostAppointedCountry = getAppointmentService().getMostAppointedCountry(month, year);

      String mostAppointedConsultantId = getConsultantService().getMostAppointedConsultantId(month, year);

      if (mostAppointedConsultantId != null) {
        Consultant consultant = getConsultantService().findUnique(mostAppointedConsultantId);
        request.setAttribute("mostAppointedConsultant", consultant);
      }

      request.setAttribute("appointments", appointments);
      request.setAttribute("totalAppointments", appointments.size());
      request.setAttribute("mostAppointedJob", mostAppointedJob);
      request.setAttribute("mostAppointedCountry", mostAppointedCountry);

    } catch (SQLException | ClassNotFoundException e) {
      feedback = "Something went wrong, Please try again.";
    } finally {
      request.setAttribute("feedback", feedback);
      request.getRequestDispatcher("report.jsp").forward(request, response);
      clearClientError();
    }
  }

  private void clearClientError() {
    feedback = null;
  }
}
