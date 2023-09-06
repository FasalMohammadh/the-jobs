package com.fasal.jobs.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.fasal.jobs.enums.DatabaseType;
import com.fasal.jobs.model.Consultant;
import com.fasal.jobs.util.database.DatabaseFactory;

public class ConsultantManagerImpl implements ConsultantManager {

  @Override
  public boolean create(Consultant consultant) throws SQLException, ClassNotFoundException {

    Connection connection = new DatabaseFactory().getDatabase(DatabaseType.MYSQL).getConnection();
    String query = "call CreateConsultant(?,?,?,?,?,?,?)";
    PreparedStatement createStatement = connection.prepareStatement(query);

    createStatement.setString(1, consultant.getId());
    createStatement.setString(2, consultant.getFirstName());
    createStatement.setString(3, consultant.getLastName());
    createStatement.setString(4, consultant.getEmail());
    createStatement.setString(5, consultant.getPhoneNumber());
    createStatement.setString(6, consultant.getCountry());
    createStatement.setString(7, consultant.getJob());

    int result = createStatement.executeUpdate();

    createStatement.close();
    connection.close();

    return result > 0;
  }

  @Override
  public boolean update(Consultant consultant) throws ClassNotFoundException, SQLException {
    Connection connection = new DatabaseFactory().getDatabase(DatabaseType.MYSQL).getConnection();
    String query = "call UpdateConsultant(?,?,?,?,?,?,?)";
    PreparedStatement updateStatement = connection.prepareStatement(query);

    updateStatement.setString(1, consultant.getFirstName());
    updateStatement.setString(2, consultant.getLastName());
    updateStatement.setString(3, consultant.getEmail());
    updateStatement.setString(4, consultant.getPhoneNumber());
    updateStatement.setString(5, consultant.getPhoneNumber());
    updateStatement.setString(6, consultant.getCountry());
    updateStatement.setString(7, consultant.getId());

    int result = updateStatement.executeUpdate();
    updateStatement.close();
    connection.close();

    return result > 0;
  }

  @Override
  public boolean delete(String id) throws SQLException, ClassNotFoundException {
    Connection connection = new DatabaseFactory().getDatabase(DatabaseType.MYSQL).getConnection();
    String query = "call DeleteConsultant(?)";
    PreparedStatement deleteStatement = connection.prepareStatement(query);
    deleteStatement.setString(1, id);

    int result = deleteStatement.executeUpdate();
    deleteStatement.close();
    connection.close();

    return result > 0;
  }

  @Override
  public Consultant findUnique(String consultantId) throws ClassNotFoundException, SQLException {
    Connection connection = new DatabaseFactory().getDatabase(DatabaseType.MYSQL).getConnection();
    String query = "call FindConsultant(?);";
    CallableStatement findStatement = connection.prepareCall(query);

    findStatement.setString(1, consultantId);
    ResultSet result = findStatement.executeQuery();

    Consultant consultant = null;
    if (result.next()) {
      consultant = new Consultant(result.getString("id"), result.getString("email"), result.getString("phone_number"),
              result.getString("first_name"), result.getString("last_name"), result.getString("created_at"),
              null, result.getString("country"), result.getString("job"));
    }

    result.close();
    findStatement.close();
    connection.close();

    return consultant;
  }

  @Override
  public List<Consultant> findMany() throws ClassNotFoundException, SQLException {
    Connection connection = new DatabaseFactory().getDatabase(DatabaseType.MYSQL).getConnection();
    String storedProcedure = "CALL FindConsultants();";
    CallableStatement findStatement = connection.prepareCall(storedProcedure);

    ResultSet result = findStatement.executeQuery(storedProcedure);
    List<Consultant> consultantList = new ArrayList<>();
    while (result.next()) {
      Consultant consultant = new Consultant(result.getString("id"), result.getString("email"), result.getString("phone_number"),
              result.getString("first_name"), result.getString("last_name"), result.getString("created_at"),
              null, result.getString("country"), result.getString("job"));

      consultantList.add(consultant);
    }

    result.close();
    findStatement.close();
    connection.close();

    return consultantList;
  }

  @Override
  public String getMostAppointedConsultantId(int month, int year) throws ClassNotFoundException, SQLException {
    Connection connection = new DatabaseFactory().getDatabase(DatabaseType.MYSQL).getConnection();
    String query = "SELECT c.id, COUNT(a.id) as appointment_count FROM consultant c JOIN appointment a on c.id = a.consultant_id WHERE MONTH(a.created_at) = ? AND YEAR(a.created_at) = ? GROUP BY a.id ORDER BY appointment_count DESC LIMIT 1;";
    PreparedStatement statement = connection.prepareStatement(query);

    statement.setInt(1, month);
    statement.setInt(2, year);

    ResultSet result = statement.executeQuery();
    String consultantId = result.next() ? result.getString("id") : null;

    result.close();
    statement.close();
    connection.close();

    return consultantId;
  }
}
