package com.fasal.jobs.dao;

import java.sql.SQLException;
import java.util.List;

import com.fasal.jobs.model.JobSeeker;

public interface JobSeekerManager {
  public boolean create(JobSeeker jobSeeker) throws ClassNotFoundException, SQLException;

  public boolean register(JobSeeker jobSeeker) throws ClassNotFoundException, SQLException;

  public boolean update(JobSeeker jobSeeker) throws ClassNotFoundException, SQLException;

  public boolean delete(String jobSeekerId) throws ClassNotFoundException, SQLException;

  public JobSeeker findUnique(String jobSeekerId) throws ClassNotFoundException, SQLException;

  public JobSeeker findUniqueByEmail(String email) throws ClassNotFoundException, SQLException;

  public List<JobSeeker> findMany() throws ClassNotFoundException, SQLException;
}
