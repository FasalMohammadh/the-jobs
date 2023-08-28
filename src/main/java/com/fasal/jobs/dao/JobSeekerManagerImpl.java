package com.fasal.jobs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.fasal.jobs.enums.DatabaseType;
import com.fasal.jobs.model.JobSeeker;
import com.fasal.jobs.util.database.DatabaseFactory;

public class JobSeekerManagerImpl implements JobSeekerManager {

  @Override
  public boolean create(JobSeeker jobSeeker) throws ClassNotFoundException, SQLException {
    Connection connection = new DatabaseFactory().getDatabase(DatabaseType.MYSQL).getConnection();
    String query = "INSERT INTO job_seeker (id,first_name,last_name,email,phone_number)  VALUES (?,?,?,?,?);";
    PreparedStatement createStatement = connection.prepareStatement(query);

    createStatement.setString(1, jobSeeker.getId());
    createStatement.setString(2, jobSeeker.getFirstName());
    createStatement.setString(3, jobSeeker.getLastName());
    createStatement.setString(4, jobSeeker.getEmail());
    createStatement.setString(5, jobSeeker.getPhoneNumber());

    int result = createStatement.executeUpdate();
    createStatement.close();
    connection.close();

    return result > 0;
  }

  @Override
  public boolean update(JobSeeker jobSeeker) throws ClassNotFoundException, SQLException {
    Connection connection = new DatabaseFactory().getDatabase(DatabaseType.MYSQL).getConnection();
    String query = "UPDATE job_seeker SET first_name=?,last_name=?,email=?,phone_number=? WHERE id=?";
    PreparedStatement updateStatement = connection.prepareStatement(query);

    updateStatement.setString(1, jobSeeker.getFirstName());
    updateStatement.setString(2, jobSeeker.getLastName());
    updateStatement.setString(3, jobSeeker.getEmail());
    updateStatement.setString(4, jobSeeker.getPhoneNumber());
    updateStatement.setString(5, jobSeeker.getId());

    int result = updateStatement.executeUpdate();
    updateStatement.close();
    connection.close();

    return result > 0;
  }

  @Override
  public boolean delete(String jobSeekerId) throws ClassNotFoundException, SQLException {
    Connection connection = new DatabaseFactory().getDatabase(DatabaseType.MYSQL).getConnection();
    String query = "Delete FROM job_seeker WHERE id=?";
    PreparedStatement deleteStatement = connection.prepareStatement(query);

    deleteStatement.setString(1, jobSeekerId);

    int result = deleteStatement.executeUpdate();
    deleteStatement.close();
    connection.close();

    return result > 0;
  }

  @Override
  public JobSeeker findUnique(String jobSeekerId) throws ClassNotFoundException, SQLException {
    Connection connection = new DatabaseFactory().getDatabase(DatabaseType.MYSQL).getConnection();
    String query = "SELECT * FROM job_seeker WHERE id=?";
    PreparedStatement findStatement = connection.prepareStatement(query);

    findStatement.setString(1, jobSeekerId);
    ResultSet result = findStatement.executeQuery();

    JobSeeker jobSeeker = null;
    if (result.next())
      jobSeeker = new JobSeeker(result.getString("id"), result.getString("email"), result.getString("phoneNumber"),
              result.getString("first_name"), result.getString("last_name"), result.getString("created_at"));

    result.close();
    findStatement.close();
    connection.close();

    return jobSeeker;
  }

  @Override
  public List<JobSeeker> findMany() throws ClassNotFoundException, SQLException {
    Connection connection = new DatabaseFactory().getDatabase(DatabaseType.MYSQL).getConnection();
    String query = "SELECT * FROM job_seeker";
    Statement findStatement = connection.createStatement();

    ResultSet result = findStatement.executeQuery(query);
    List<JobSeeker> jobSeekerList = new ArrayList<>();
    while (result.next()) {
      JobSeeker jobSeeker = new JobSeeker(result.getString("id"), result.getString("email"),
              result.getString("phone_number"),
              result.getString("first_name"), result.getString("last_name"), result.getString("created_at"));

      jobSeekerList.add(jobSeeker);
    }

    result.close();
    findStatement.close();
    connection.close();

    return jobSeekerList;
  }
}
