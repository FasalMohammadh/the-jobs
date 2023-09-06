package com.fasal.jobs.controller;

import com.fasal.jobs.enums.SessionUser;
import com.fasal.jobs.service.EmployeeService;
import com.fasal.jobs.util.helper.Helper;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EmployeeController extends HttpServlet {

  private EmployeeService getEmployeeService() {
    return EmployeeService.getService();
  }

  private static Helper getHelper() {
    return Helper.getHelper();
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.getRequestDispatcher("login.jsp").forward(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    String actionType = request.getParameter("actionType");
    switch (actionType) {
      case "LOGIN" -> login(request, response);
      case "LOGOUT" -> logout(request, response);
    }
  }

  public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String feedback = null;
    boolean isAuthenticated = false;

    String email = request.getParameter("email");
    String password = request.getParameter("password");


    try {
      isAuthenticated = getEmployeeService().login(email, password);

      if (isAuthenticated) {
        getHelper().setUserSession(request.getSession(), SessionUser.EMPLOYEE, email);
        response.sendRedirect("appointment");
      } else {
        feedback = "Entered email or password incorrect.";
      }

    } catch (Exception e) {
      feedback = "Something went wrong.";
    } finally {
      if (!isAuthenticated) {
        request.setAttribute("feedback", feedback);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
        requestDispatcher.forward(request, response);
      }
    }

  }


  public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    getHelper().removeUserSession(request.getSession());

    RequestDispatcher requestDispatcher = request.getRequestDispatcher("employee-login.jsp");
    requestDispatcher.forward(request, response);
  }

}
