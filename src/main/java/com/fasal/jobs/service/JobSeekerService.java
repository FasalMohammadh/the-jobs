package com.fasal.jobs.service;

import java.sql.SQLException;
import java.util.List;

import com.fasal.jobs.dao.JobSeekerManager;
import com.fasal.jobs.dao.JobSeekerManagerImpl;
import com.fasal.jobs.model.JobSeeker;

public class JobSeekerService {
  private static JobSeekerService jobSeekerService;

  public static synchronized JobSeekerService getService() {
    if (jobSeekerService == null)
      jobSeekerService = new JobSeekerService();

    return jobSeekerService;
  }

  private JobSeekerManager getJobSeekerManager() {
    return new JobSeekerManagerImpl();
  }

  public boolean create(JobSeeker jobSeeker) throws ClassNotFoundException, SQLException {
    return getJobSeekerManager().create(jobSeeker);
  }

  public boolean update(JobSeeker jobSeeker) throws ClassNotFoundException, SQLException {
    return getJobSeekerManager().update(jobSeeker);
  }

  public boolean delete(String id) throws ClassNotFoundException, SQLException {
    return getJobSeekerManager().delete(id);
  }

  public JobSeeker findUnique(String id) throws ClassNotFoundException, SQLException {
    return getJobSeekerManager().findUnique(id);
  }

  public List<JobSeeker> findMany() throws ClassNotFoundException, SQLException {
    return getJobSeekerManager().findMany();
  }
}
