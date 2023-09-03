package com.fasal.jobs.dao;

import com.fasal.jobs.enums.AppointmentStatus;
import com.fasal.jobs.enums.DatabaseType;
import com.fasal.jobs.model.Appointment;
import com.fasal.jobs.util.database.DatabaseFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AppointmentManagerImpl implements AppointmentManager {

  private Connection getMysqlConnection() throws ClassNotFoundException, SQLException {
    return new DatabaseFactory().getDatabase(DatabaseType.MYSQL).getConnection();
  }

  @Override
  public boolean create(Appointment appointment) throws SQLException, ClassNotFoundException {
    Connection connection = getMysqlConnection();
    String query = "INSERT INTO appointment(id,date_time, job, country, job_seeker_id, consultant_id, status) VALUES(?,?,?,?,?,?,?);";
    PreparedStatement createStatement = connection.prepareStatement(query);

    createStatement.setString(1, appointment.getId());
    createStatement.setTimestamp(2, Timestamp.valueOf(appointment.getDateTime()));
    createStatement.setString(3, appointment.getJob());
    createStatement.setString(4, appointment.getCountry());
    createStatement.setString(5, appointment.getJobSeekerId());
    createStatement.setString(6, appointment.getConsultantId());
    createStatement.setString(7, appointment.getStatus().toString());

    int result = createStatement.executeUpdate();
    createStatement.close();
    connection.close();

    return result > 0;
  }

  @Override
  public boolean update(Appointment appointment) throws ClassNotFoundException, SQLException {
    Connection connection = getMysqlConnection();
    String query = "UPDATE appointment SET date_time=?, job=?, country=?, job_seeker_id=?, consultant_id=?, status=? WHERE id=?;";
    PreparedStatement updateStatement = connection.prepareStatement(query);

    updateStatement.setTimestamp(1, Timestamp.valueOf(appointment.getDateTime()));
    updateStatement.setString(2, appointment.getJob());
    updateStatement.setString(3, appointment.getCountry());
    updateStatement.setString(4, appointment.getJobSeekerId());
    updateStatement.setString(5, appointment.getConsultantId());
    updateStatement.setString(6, appointment.getStatus().toString());
    updateStatement.setString(7, appointment.getId());


    int result = updateStatement.executeUpdate();
    updateStatement.close();
    connection.close();

    return result > 0;
  }

  @Override
  public boolean delete(String id) throws SQLException, ClassNotFoundException {
    Connection connection = getMysqlConnection();
    String query = "DELETE FROM appointment WHERE id=?";
    PreparedStatement deleteStatement = connection.prepareStatement(query);
    deleteStatement.setString(1, id);

    int result = deleteStatement.executeUpdate();
    deleteStatement.close();
    connection.close();

    return result > 0;
  }

  @Override
  public Appointment findUnique(String appointmentId) throws ClassNotFoundException, SQLException {
    Connection connection = getMysqlConnection();
    String query = "SELECT * FROM appointment WHERE id=?";
    PreparedStatement findStatement = connection.prepareStatement(query);

    findStatement.setString(1, appointmentId);
    ResultSet result = findStatement.executeQuery();

    Appointment appointment = null;
    if (result.next())
      appointment = new Appointment(
              result.getString("id"), result.getTimestamp("date_time").toLocalDateTime(), result.getString("consultant_id"),
              result.getString("job_seeker_id"),
              result.getString("country"), result.getString("job"),
              result.getDate("created_at"), AppointmentStatus.valueOf(result.getString("status")));

    result.close();
    findStatement.close();
    connection.close();

    return appointment;
  }

  @Override
  public List<Appointment> findMany() throws ClassNotFoundException, SQLException {
    Connection connection = getMysqlConnection();
    String query = "SELECT * FROM appointment";
    Statement findStatement = connection.createStatement();

    ResultSet result = findStatement.executeQuery(query);
    List<Appointment> appointmentList = new ArrayList<>();
    while (result.next()) {
      Appointment appointment = new Appointment(
              result.getString("id"), result.getTimestamp("date_time").toLocalDateTime(), result.getString("consultant_id"),
              result.getString("job_seeker_id"),
              result.getString("country"), result.getString("job"),
              result.getDate("created_at"), AppointmentStatus.valueOf(result.getString("status")));

      appointmentList.add(appointment);
    }

    result.close();
    findStatement.close();
    connection.close();

    return appointmentList;
  }

  @Override
  public List<Appointment> findMany(int month, int year) throws ClassNotFoundException, SQLException {
    Connection connection = getMysqlConnection();
    String query = "SELECT * FROM appointment WHERE MONTH(created_at) = ? AND Year(created_at) = ?";
    PreparedStatement findStatement = connection.prepareStatement(query);

    findStatement.setInt(1, month);
    findStatement.setInt(2, year);

    ResultSet result = findStatement.executeQuery();
    List<Appointment> appointmentList = new ArrayList<>();
    while (result.next()) {
      Appointment appointment = new Appointment(
              result.getString("id"), result.getTimestamp("date_time").toLocalDateTime(), result.getString("consultant_id"),
              result.getString("job_seeker_id"),
              result.getString("country"), result.getString("job"),
              result.getDate("created_at"), AppointmentStatus.valueOf(result.getString("status")));

      appointmentList.add(appointment);
    }

    result.close();
    findStatement.close();
    connection.close();

    return appointmentList;
  }

  @Override
  public String getMostAppointedJob(int month, int year) throws ClassNotFoundException, SQLException {
    Connection connection = getMysqlConnection();
    String query = "SELECT job, COUNT(job) AS job_count FROM appointment WHERE MONTH(created_at) = ? AND YEAR(created_at) = ? GROUP BY job ORDER BY job_count DESC LIMIT 1;";
    PreparedStatement statement = connection.prepareStatement(query);

    statement.setInt(1, month);
    statement.setInt(2, year);

    ResultSet result = statement.executeQuery();
    String job = result.next() ? result.getString("job") : null;

    result.close();
    statement.close();
    connection.close();

    return job;
  }

  @Override
  public String getMostAppointedCountry(int month, int year) throws ClassNotFoundException, SQLException {
    Connection connection = getMysqlConnection();
    String query = "SELECT country, COUNT(country) AS country_count FROM appointment WHERE MONTH(created_at)=? AND YEAR(created_at)=? GROUP BY country ORDER BY country_count DESC LIMIT 1;";
    PreparedStatement statement = connection.prepareStatement(query);

    statement.setInt(1, month);
    statement.setInt(2, year);

    ResultSet result = statement.executeQuery();

    String country = result.next() ? result.getString("country") : null;

    result.close();
    statement.close();
    connection.close();

    return country;
  }


}
