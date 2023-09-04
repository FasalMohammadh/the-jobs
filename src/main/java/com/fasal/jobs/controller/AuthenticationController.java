package com.fasal.jobs.controller;

import com.fasal.jobs.service.AuthenticationService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationController extends HttpServlet {

  private AuthenticationService getAuthenticationService() {
    return AuthenticationService.getService();
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.getRequestDispatcher("index.jsp").forward(request, response);
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
      isAuthenticated = getAuthenticationService().login(email, password, request.getSession());

      if (isAuthenticated) {
        response.sendRedirect("appointment");
      } else {
        feedback = "Entered email or password incorrect.";
      }

    } catch (Exception e) {
      feedback = "Something went wrong.";
    } finally {
      if (!isAuthenticated) {
        request.setAttribute("feedback", feedback);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
        requestDispatcher.forward(request, response);
      }
    }

  }

  public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    String feedback = null;
    boolean isLoggedOut = false;
    try {
      isLoggedOut = getAuthenticationService().logout(request.getSession());

      if (isLoggedOut) response.sendRedirect("index.jsp");
      else
        feedback = "Unable to logout, Something is wrong.";

    } catch (IOException e) {
      feedback = "Unable to logout, Something is wrong.";
    } finally {
      if (!isLoggedOut) {
        request.setAttribute("feedback", feedback);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("appointment.jsp");
        requestDispatcher.forward(request, response);
      }
    }
  }
}
