package com.fasal.jobs.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasal.jobs.enums.ActionType;
import com.fasal.jobs.enums.Day;
import com.fasal.jobs.enums.SessionUser;
import com.fasal.jobs.model.Consultant;
import com.fasal.jobs.model.ConsultantAvailability;
import com.fasal.jobs.service.ConsultantService;
import com.fasal.jobs.util.helper.Helper;

public class ConsultantController extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String actionTypeRaw = request.getParameter("actionType");
    ActionType actionType = actionTypeRaw == null ? null : ActionType.valueOf(request.getParameter("actionType"));
    if (actionType == ActionType.GET)
      getConsultant(request, response);
    else
      getConsultants(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    ActionType actionType = ActionType.valueOf(request.getParameter("actionType"));
    switch (actionType) {
      case CREATE -> createConsultant(request, response);
      case UPDATE -> updateConsultant(request, response);
      case DELETE -> deleteConsultant(request, response);
    }
  }

  private void createConsultant(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String feedback = null;
    boolean hasErrored = false;
    try {
      String id = UUID.randomUUID().toString();
      String email = request.getParameter("email");
      String phoneNumber = request.getParameter("phoneNumber");
      String firstName = request.getParameter("firstName");
      String lastName = request.getParameter("lastName");
      String country = request.getParameter("country");
      String job = request.getParameter("job");
      List<ConsultantAvailability> availability = new ArrayList<>();

      String[] availableDays = request.getParameterValues("day[]");
      String[] startTime = request.getParameterValues("startTime[]");
      String[] endTime = request.getParameterValues("endTime[]");

      for (int i = 0; i < availableDays.length; i++) {
        ConsultantAvailability consultantAvailability = new ConsultantAvailability(null, Day.valueOf(availableDays[i]), startTime[i], endTime[i], id);
        availability.add(consultantAvailability);
      }

      Consultant consultant = new Consultant(id, email, phoneNumber, firstName, lastName, availability
              , country, job);
      boolean isCreated = ConsultantService.getService().create(consultant);

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
      response.sendRedirect("consultant");
    }
  }

  private void updateConsultant(HttpServletRequest request, HttpServletResponse response) throws IOException {
    boolean hasErrored = false;
    String feedback = null;
    try {
      String id = request.getParameter("id");
      String email = request.getParameter("email");
      String phoneNumber = request.getParameter("phoneNumber");
      String firstName = request.getParameter("firstName");
      String lastName = request.getParameter("lastName");
      String country = request.getParameter("country");
      String job = request.getParameter("job");
      List<ConsultantAvailability> availability = new ArrayList<>();

      String[] availableIds = request.getParameterValues("id[]");
      String[] availableDays = request.getParameterValues("day[]");
      String[] startTime = request.getParameterValues("startTime[]");
      String[] endTime = request.getParameterValues("endTime[]");

      for (int i = 0; i < availableDays.length; i++) {
        ConsultantAvailability consultantAvailability = new ConsultantAvailability(
                availableIds[i].isEmpty() ? null : availableIds[i],
                Day.valueOf(availableDays[i]),
                startTime[i],
                endTime[i],
                id
        );
        availability.add(consultantAvailability);
      }

      Consultant consultant = new Consultant(id, email, phoneNumber, firstName, lastName, availability
              , country, job);

      boolean isUpdated = ConsultantService.getService().update(consultant);
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
      response.sendRedirect("consultant");
    }
  }

  private void deleteConsultant(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String feedback = null;
    boolean hasErrored = false;
    try {
      String id = request.getParameter("id");
      boolean isDeleted = ConsultantService.getService().delete(id);
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
      response.sendRedirect("consultant");
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
    HttpSession session = request.getSession();
    try {
      List<Consultant> consultants = ConsultantService.getService().findMany();
      request.setAttribute("consultants", consultants.isEmpty() ? null : consultants);
      RequestDispatcher requestDispatcher = request.getRequestDispatcher("consultant.jsp");
      requestDispatcher.forward(request, response);

    } catch (ClassNotFoundException | SQLException exception) {
      exception.printStackTrace();
    } finally {
      session.removeAttribute("feedback");
    }
  }
}
