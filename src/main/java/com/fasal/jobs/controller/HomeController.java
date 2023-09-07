package com.fasal.jobs.controller;

import com.fasal.jobs.model.Consultant;
import com.fasal.jobs.service.ConsultantService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class HomeController extends HttpServlet {
  private String feedback = null;

  private static ConsultantService getConsultantService() {
    return ConsultantService.getService();
  }
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    getConsultants(request, response);
  }

  private void getConsultants(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
      List<Consultant> consultants = getConsultantService().findMany();
      request.setAttribute("consultants", consultants.isEmpty() ? null : consultants);
    } catch (ClassNotFoundException | SQLException exception) {
      feedback = "Something went wrong, please try again later.";
    } finally {
      request.setAttribute("feedback", feedback);
      RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
      requestDispatcher.forward(request, response);

      clearFeedback();
    }
  }


  private void clearFeedback() {
    feedback = null;
  }
}
