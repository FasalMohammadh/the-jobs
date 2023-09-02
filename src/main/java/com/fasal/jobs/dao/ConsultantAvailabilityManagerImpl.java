package com.fasal.jobs.dao;

import com.fasal.jobs.enums.DatabaseType;
import com.fasal.jobs.enums.Day;
import com.fasal.jobs.model.ConsultantAvailability;
import com.fasal.jobs.util.database.DatabaseFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConsultantAvailabilityManagerImpl implements ConsultantAvailabilityManager {

  private Connection getMysqlConnection() throws SQLException, ClassNotFoundException {
    return new DatabaseFactory().getDatabase(DatabaseType.MYSQL).getConnection();
  }

  public boolean create(ConsultantAvailability consultantAvailability) throws ClassNotFoundException, SQLException {
    Connection connection = getMysqlConnection();
    String query = "INSERT INTO consultant_availability (id, day,start_time,end_time,consultant_id) VALUES (?, ?, ?, ?, ?);";
    PreparedStatement createStatement = connection.prepareStatement(query);

    createStatement.setString(1, consultantAvailability.getId());
    createStatement.setString(2, consultantAvailability.getDay().toString());
    createStatement.setString(3, consultantAvailability.getStartTime());
    createStatement.setString(4, consultantAvailability.getEndTime());
    createStatement.setString(5, consultantAvailability.getConsultantId());

    int result = createStatement.executeUpdate();

    createStatement.close();
    connection.close();

    return result > 0;
  }

  public boolean update(ConsultantAvailability consultantAvailability) throws ClassNotFoundException, SQLException {
    Connection connection = getMysqlConnection();
    String query = "UPDATE consultant_availability SET day=?,start_time=?, end_time=? WHERE id=?";
    PreparedStatement updateStatement = connection.prepareStatement(query);

    updateStatement.setString(1, consultantAvailability.getDay().toString());
    updateStatement.setString(2, consultantAvailability.getStartTime());
    updateStatement.setString(3, consultantAvailability.getEndTime());
    updateStatement.setString(4, consultantAvailability.getId());

    int result = updateStatement.executeUpdate();

    updateStatement.close();
    connection.close();

    return result > 0;
  }

  public boolean delete(String id) throws ClassNotFoundException, SQLException {
    Connection connection = getMysqlConnection();
    String query = "DELETE FROM consultant_availability where id=?";
    PreparedStatement deleteStatement = connection.prepareStatement(query);

    deleteStatement.setString(1, id);

    int result = deleteStatement.executeUpdate();

    deleteStatement.close();
    connection.close();

    return result > 0;
  }

  public ConsultantAvailability findUnique(String id) throws SQLException, ClassNotFoundException,
          IllegalArgumentException {
    Connection connection = getMysqlConnection();
    String query = "SELECT * FROM consultant_availability WHERE id=?";
    PreparedStatement findStatement = connection.prepareStatement(query);

    findStatement.setString(1, id);
    ResultSet result = findStatement.executeQuery();

    ConsultantAvailability consultantAvailablity = null;
    if (result.next())
      consultantAvailablity = new ConsultantAvailability(
              result.getString("id"),
              Day.valueOf(result.getString("day")),
              result.getString("start_time"),
              result.getString("end_time"),
              result.getString("consultant_id")
      );


    result.close();
    findStatement.close();
    connection.close();

    return consultantAvailablity;
  }

  public List<ConsultantAvailability> findMany() throws SQLException, ClassNotFoundException,
          IllegalArgumentException {
    Connection connection = getMysqlConnection();
    String query = "SELECT * FROM consultant_availability";
    Statement findStatement = connection.createStatement();

    ResultSet result = findStatement.executeQuery(query);

    List<ConsultantAvailability> consultantAvailabilities = extractConsultantAvailabilities(result);

    result.close();
    findStatement.close();
    connection.close();

    return consultantAvailabilities;
  }

  public List<ConsultantAvailability> findMany(String consultantId) throws SQLException, ClassNotFoundException,
          IllegalArgumentException {
    Connection connection = getMysqlConnection();
    String query = "SELECT * FROM consultant_availability WHERE consultant_id=?";
    PreparedStatement findStatement = connection.prepareStatement(query);

    findStatement.setString(1, consultantId);

    ResultSet result = findStatement.executeQuery();

    List<ConsultantAvailability> consultantAvailabilities = extractConsultantAvailabilities(result);

    result.close();
    findStatement.close();
    connection.close();

    return consultantAvailabilities;
  }

  private List<ConsultantAvailability> extractConsultantAvailabilities(ResultSet result) throws SQLException {
    List<ConsultantAvailability> consultantAvailabilities = new ArrayList<>();
    while (result.next()) {

      ConsultantAvailability consultantAvailability = new ConsultantAvailability(
              result.getString("id"),
              Day.valueOf(result.getString("day")),
              result.getString("start_time"),
              result.getString("end_time"),
              result.getString("consultant_id")
      );
      consultantAvailabilities.add(consultantAvailability);
    }

    return consultantAvailabilities;
  }


}
