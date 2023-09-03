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
import javax.servlet.http.HttpSession;

import com.fasal.jobs.enums.ActionType;
import com.fasal.jobs.model.JobSeeker;
import com.fasal.jobs.service.JobSeekerService;
import com.fasal.jobs.util.helper.Helper;

public class JobSeekerController extends HttpServlet {

  private JobSeekerService getJobSeekerService() {
    return JobSeekerService.getService();
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    getJobSeekers(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    ActionType actionType = ActionType.valueOf(request.getParameter("actionType"));
    switch (actionType) {
      case CREATE -> createJobSeeker(request, response);
      case UPDATE -> updateJobSeeker(request, response);
      case DELETE -> deleteJobSeeker(request, response);
    }
  }

  private void createJobSeeker(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String feedback = null;
    boolean hasErrored = false;
    try {
      boolean isAuthorized = Helper.getHelper().isAuthorized(request.getSession());

      if (!isAuthorized) {
        response.sendRedirect("index.jsp");
        return;
      }

      String id = UUID.randomUUID().toString();
      String email = request.getParameter("email");
      String phoneNumber = request.getParameter("phoneNumber");
      String firstName = request.getParameter("firstName");
      String lastName = request.getParameter("lastName");

      JobSeeker jobSeeker = new JobSeeker(id, email, phoneNumber, firstName, lastName);

      boolean isCreated = getJobSeekerService().create(jobSeeker);
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
      response.sendRedirect("job-seeker");
    }
  }

  private void updateJobSeeker(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String feedback = null;
    boolean hasErrored = false;
    try {
      boolean isAuthorized = Helper.getHelper().isAuthorized(request.getSession());

      if (!isAuthorized) {
        response.sendRedirect("index.jsp");
        return;
      }

      String id = request.getParameter("id");
      String email = request.getParameter("email");
      String phoneNumber = request.getParameter("phoneNumber");
      String firstName = request.getParameter("firstName");
      String lastName = request.getParameter("lastName");

      JobSeeker jobSeeker = new JobSeeker(id, email, phoneNumber, firstName, lastName);

      boolean isUpdated = getJobSeekerService().update(jobSeeker);
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
      response.sendRedirect("job-seeker");
    }
  }

  private void deleteJobSeeker(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String feedback = null;
    boolean hasErrored = false;
    try {
      boolean isAuthorized = Helper.getHelper().isAuthorized(request.getSession());

      if (!isAuthorized) {
        response.sendRedirect("index.jsp");
        return;
      }

      String id = request.getParameter("id");
      boolean isDeleted = getJobSeekerService().delete(id);

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
      response.sendRedirect("job-seeker");
    }
  }

  private void getJobSeekers(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    HttpSession session = request.getSession();
    try {
      boolean isAuthorized = Helper.getHelper().isAuthorized(request.getSession());

      if (!isAuthorized) {
        response.sendRedirect("index.jsp");
        return;
      }

      List<JobSeeker> jobSeekers = getJobSeekerService().findMany();
      request.setAttribute("jobSeekers", jobSeekers.isEmpty() ? null : jobSeekers);
      request.setAttribute("feedback", session.getAttribute("feedback"));
      RequestDispatcher requestDispatcher = request.getRequestDispatcher("job-seeker.jsp");
      requestDispatcher.forward(request, response);
    } catch (ClassNotFoundException | SQLException exception) {
      exception.printStackTrace();
    } finally {
      session.removeAttribute("feedback");
    }
  }
}
