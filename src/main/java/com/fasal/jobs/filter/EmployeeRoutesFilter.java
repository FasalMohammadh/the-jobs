package com.fasal.jobs.filter;

import com.fasal.jobs.enums.ActionType;
import com.fasal.jobs.enums.SessionUser;
import com.fasal.jobs.util.helper.Helper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EmployeeRoutesFilter implements Filter {


  @Override
  public void init(FilterConfig filterConfig) throws ServletException {


  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    HttpServletRequest req = (HttpServletRequest) request;
    HttpServletResponse resp = (HttpServletResponse) response;

    boolean isJobSeekerLoginRequest = req.getRequestURL().toString().contains("/job-seeker");
    String actionType = req.getParameter("actionType");
    if (isJobSeekerLoginRequest && actionType != null &&
            (ActionType.valueOf(actionType) == ActionType.LOGIN || ActionType.valueOf(actionType) == ActionType.REGISTER || ActionType.valueOf(actionType) == ActionType.LOGOUT)) {
      chain.doFilter(request, response);
      return;
    }

    boolean isLoggedAsEmployee = Helper.getHelper().isLoggedAs(req.getSession(), SessionUser.EMPLOYEE);
    if (isLoggedAsEmployee) {
      chain.doFilter(request, response);
      return;
    }

    resp.sendRedirect("employee-login.jsp");
  }

  @Override
  public void destroy() {

  }
}
