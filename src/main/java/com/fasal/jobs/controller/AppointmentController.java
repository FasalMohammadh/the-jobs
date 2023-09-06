package com.fasal.jobs.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasal.jobs.enums.ActionType;
import com.fasal.jobs.enums.AppointmentStatus;
import com.fasal.jobs.enums.SessionUser;
import com.fasal.jobs.model.Appointment;
import com.fasal.jobs.model.Consultant;
import com.fasal.jobs.model.JobSeeker;
import com.fasal.jobs.service.AppointmentService;
import com.fasal.jobs.service.ConsultantService;
import com.fasal.jobs.service.JobSeekerService;
import com.fasal.jobs.util.helper.Helper;

public class AppointmentController extends HttpServlet {

  private AppointmentService getAppointmentService() {
    return AppointmentService.getService();
  }

  private ConsultantService getConsultantService() {
    return ConsultantService.getService();
  }

  private JobSeekerService getJobSeekerService() {
    return JobSeekerService.getService();
  }


  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    getAppointments(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
      // no default
    }
  }

  private void createAppointment(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String feedback = null;
    boolean hasErrored = false;
    try {
      String id = UUID.randomUUID().toString();
      String date = request.getParameter("date");
      String time = request.getParameter("time");
      String consultantId = request.getParameter("consultant");
      String jobSeekerId = request.getParameter("jobSeeker");
      String country = request.getParameter("country");
      String job = request.getParameter("job");

      LocalDateTime dateTime = Helper.getHelper().toLocalDateTime(date, time);

      Appointment appointment = new Appointment(id, dateTime, consultantId, jobSeekerId, country, job, null,
              AppointmentStatus.CREATED);

      boolean isCreated = getAppointmentService().create(appointment);
      if (!isCreated) {
        hasErrored = true;
        feedback = "Something went wrong, Failed to create.";
      }
    } catch (ClassNotFoundException | SQLException e) {
      hasErrored = true;
      feedback = "Something went wrong, Failed to create.";
      e.printStackTrace();
    } finally {
      if (hasErrored) {
        HttpSession session = request.getSession();
        session.setAttribute("feedback", feedback);
      }
      response.sendRedirect("appointment");
    }
  }

  private void updateAppointment(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String feedback = null;
    boolean hasErrored = false;

    try {

      String id = request.getParameter("id");
      String date = request.getParameter("date");
      String time = request.getParameter("time");
      String consultantId = request.getParameter("consultant");
      String jobSeekerId = request.getParameter("jobSeeker");
      String country = request.getParameter("country");
      String job = request.getParameter("job");
      AppointmentStatus status = AppointmentStatus.valueOf(request.getParameter("status"));

      LocalDateTime dateTime = Helper.getHelper().toLocalDateTime(date, time);

      Appointment appointment = new Appointment(id, dateTime, consultantId, jobSeekerId, country, job, null,
              status);

      boolean isUpdated = getAppointmentService().update(appointment);
      if (!isUpdated) {
        hasErrored = true;
        feedback = "Something went wrong, Failed to update.";
      }
    } catch (ClassNotFoundException | SQLException e) {
      hasErrored = true;
      feedback = "Something went wrong, Failed to update.";
      e.printStackTrace();
    } finally {
      if (hasErrored) {
        HttpSession session = request.getSession();
        session.setAttribute("feedback", feedback);
      }
      response.sendRedirect("appointment");
    }
  }

  private void deleteAppointment(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String feedback = null;
    boolean hasErrored = false;
    try {
      String id = request.getParameter("id");
      boolean isDeleted = getAppointmentService().delete(id);

      if (!isDeleted) {
        hasErrored = true;
        feedback = "Something went wrong, Failed to delete.";
      }
    } catch (ClassNotFoundException | SQLException e) {
      hasErrored = true;
      feedback = "Something went wrong, Failed to delete.";
      e.printStackTrace();
    } finally {
      if (hasErrored) {
        HttpSession session = request.getSession();
        session.setAttribute("feedback", feedback);
      }
      response.sendRedirect("appointment");
    }
  }

  private void getAppointments(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    HttpSession session = request.getSession();

    try {
      List<Appointment> appointments = getAppointmentService().findMany();
      List<Consultant> consultants = getConsultantService().findMany();
      List<JobSeeker> jobSeekers = getJobSeekerService().findMany();

      request.setAttribute("appointments", appointments);
      request.setAttribute("consultants", consultants);
      request.setAttribute("jobSeekers", jobSeekers);

      RequestDispatcher requestDispatcher = request.getRequestDispatcher("appointment.jsp");
      requestDispatcher.forward(request, response);

    } catch (ClassNotFoundException | SQLException exception) {
      exception.printStackTrace();
    } finally {
      session.removeAttribute("feedback");
    }
  }
}
