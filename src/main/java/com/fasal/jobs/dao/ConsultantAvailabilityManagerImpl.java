package com.fasal.jobs.dao;

import com.fasal.jobs.enums.DatabaseType;
import com.fasal.jobs.model.Consultant;
import com.fasal.jobs.model.ConsultantAvailability;
import com.fasal.jobs.util.database.DatabaseFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConsultantAvailabilityManagerImpl implements ConsultantAvailabilityManager {
  public boolean create(ConsultantAvailability consultantAvailability, String consultantId) throws ClassNotFoundException, SQLException {
    Connection connection = new DatabaseFactory().getDatabase(DatabaseType.MYSQL).getConnection();
    String query = "INSERT INTO consultant_availability (id, day,start_time,end_time,consultant_id) VALUES (?, ?, ?, ?, ?);";
    PreparedStatement createStatement = connection.prepareStatement(query);

    createStatement.setString(1, consultantAvailability.getId());
    createStatement.setString(2, consultantAvailability.getDay().toString());
    createStatement.setString(3, consultantAvailability.getStartTime());
    createStatement.setString(4, consultantAvailability.getEndTime());
    createStatement.setString(5, consultantId);

    int result = createStatement.executeUpdate();

    createStatement.close();
    connection.close();

    return result > 0;
  }

  public boolean update(ConsultantAvailability consultantAvailability) throws ClassNotFoundException, SQLException {
    Connection connection = new DatabaseFactory().getDatabase(DatabaseType.MYSQL).getConnection();
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
    Connection connection = new DatabaseFactory().getDatabase(DatabaseType.MYSQL).getConnection();
    String query = "DELETE FROM consultant_availability where id=?";
    PreparedStatement deleteStatement = connection.prepareStatement(query);

    deleteStatement.setString(1, id);

    int result = deleteStatement.executeUpdate();

    deleteStatement.close();
    connection.close();

    return result > 0;
  }

}
