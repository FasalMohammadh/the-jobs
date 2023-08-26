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
import com.fasal.jobs.model.Consultant;
import com.fasal.jobs.model.ConsultantAvailability;
import com.fasal.jobs.model.ConsultantSpecialization;
import com.fasal.jobs.service.ConsultantService;

public class ConsultantController extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    ActionType actionType = ActionType.valueOf(request.getParameter("actionType"));
    switch (actionType) {
      case GET:
        getConsultant(request, response);
        break;
      default:
        getConsultants(request, response);
    }
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    ActionType actionType = ActionType.valueOf(request.getParameter("actionType"));
    switch (actionType) {
      case CREATE:
        createConsultant(request, response);
        break;
      case UPDATE:
        updateConsultant(request, response);
        break;
      case DELETE:
        deleteConsultant(request, response);
        break;
    }
  }

  private void createConsultant(HttpServletRequest request, HttpServletResponse response) {
    try {
      String id = request.getParameter("id");
      String email = request.getParameter("email");
      String phoneNumber = request.getParameter("phoneNumber");
      String firstName = request.getParameter("firstName");
      String lastName = request.getParameter("lastName");
      // List<ConsultantAvailability> availability,
      // List<ConsultantSpecialization> specialization

      Consultant consultant = new Consultant(id, email, phoneNumber, firstName, lastName);
      boolean isCreated = ConsultantService.getService().create(consultant);

      if (isCreated) {

      } else {

      }
    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
    }
  }

  private void updateConsultant(HttpServletRequest request, HttpServletResponse response) {
    try {
      String id = request.getParameter("id");
      String email = request.getParameter("email");
      String phoneNumber = request.getParameter("phoneNumber");
      String firstName = request.getParameter("firstName");
      String lastName = request.getParameter("lastName");
      // List<ConsultantAvailability> availability,
      // List<ConsultantSpecialization> specialization

      Consultant consultant = new Consultant(id, email, phoneNumber, firstName, lastName);

      boolean isUpdated = ConsultantService.getService().update(consultant);
      if (isUpdated) {

      } else {

      }
    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
    }
  }

  private void deleteConsultant(HttpServletRequest request, HttpServletResponse response) {
    try {
      String id = request.getParameter("id");
      boolean isDeleted = ConsultantService.getService().delete(id);

      if (isDeleted) {

      } else {

      }
    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
    }
  }

  private void getConsultant(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String consultantId = request.getParameter("consultantId");

    try {
      Consultant consultant = ConsultantService.getService().findUnique(consultantId);

      request.setAttribute("consultant", consultant);
      RequestDispatcher requestDispatcher = request.getRequestDispatcher("admin-consultant.jsp");
      requestDispatcher.forward(request, response);
    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
    }
  }

  private void getConsultants(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    List<Consultant> consultants;
    try {
      consultants = ConsultantService.getService().findMany();
      request.setAttribute("consultants", consultants);
      RequestDispatcher requestDispatcher = request.getRequestDispatcher("consultant.jsp");
      requestDispatcher.forward(request, response);
    } catch (ClassNotFoundException | SQLException exception) {
      exception.printStackTrace();
    }
  }
}
