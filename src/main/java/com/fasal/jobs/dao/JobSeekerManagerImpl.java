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
    String query = "call CreateJobSeeker(?,?,?,?,?)";
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
    String query = "call UpdateJobSeeker(?,?,?,?,?)";
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
    String query = "call DeleteJobSeeker(?)";
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
    String query = "call FindJobSeeker(?)";
    PreparedStatement findStatement = connection.prepareStatement(query);

    findStatement.setString(1, jobSeekerId);
    ResultSet result = findStatement.executeQuery();

    JobSeeker jobSeeker = null;
    if (result.next())
      jobSeeker = getJobSeekerFromResult(result);

    result.close();
    findStatement.close();
    connection.close();

    return jobSeeker;
  }

  @Override
  public List<JobSeeker> findMany() throws ClassNotFoundException, SQLException {
    Connection connection = new DatabaseFactory().getDatabase(DatabaseType.MYSQL).getConnection();
    String query = "call FindJobSeekers()";
    Statement findStatement = connection.createStatement();

    ResultSet result = findStatement.executeQuery(query);
    List<JobSeeker> jobSeekerList = new ArrayList<>();
    while (result.next()) {
      JobSeeker jobSeeker = getJobSeekerFromResult(result);
      jobSeekerList.add(jobSeeker);
    }

    result.close();
    findStatement.close();
    connection.close();

    return jobSeekerList;
  }

  private JobSeeker getJobSeekerFromResult(ResultSet result) throws SQLException {
    JobSeeker jobSeeker = new JobSeeker(
            result.getString("id"),
            result.getString("email"),
            result.getString("phone_number"),
            result.getString("first_name"),
            result.getString("last_name"),
            result.getString("created_at")
    );

    return jobSeeker;
  }


}
