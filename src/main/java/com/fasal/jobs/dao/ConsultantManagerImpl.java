package com.fasal.jobs.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.fasal.jobs.enums.DatabaseType;
import com.fasal.jobs.enums.Day;
import com.fasal.jobs.model.Consultant;
import com.fasal.jobs.model.ConsultantAvailability;
import com.fasal.jobs.model.ConsultantSpecialization;
import com.fasal.jobs.util.database.DatabaseFactory;

public class ConsultantManagerImpl implements ConsultantManager {
  @Override
  public boolean create(Consultant consultant) throws SQLException, ClassNotFoundException {
    Connection connection = new DatabaseFactory().getDatabase(DatabaseType.MYSQL).getConnection();
    String query = "INSERT INTO consultant (id, first_name, last_name, email, phone_number) VALUES (?, ?, ?, ?, ?);";
    PreparedStatement createStatement = connection.prepareStatement(query);

    createStatement.setString(1, consultant.getId());
    createStatement.setString(2, consultant.getFirstName());
    createStatement.setString(3, consultant.getLastName());
    createStatement.setString(4, consultant.getEmail());
    createStatement.setString(5, consultant.getPhoneNumber());

    int result = createStatement.executeUpdate();
    createStatement.close();
    connection.close();

    return result > 0;
  }

  @Override
  public boolean update(Consultant consultant) throws ClassNotFoundException, SQLException {
    Connection connection = new DatabaseFactory().getDatabase(DatabaseType.MYSQL).getConnection();
    String query = "UPDATE consultant SET first_name = ?, last_name = ?, email = ?, phone_number = ? WHERE id = ?;";
    PreparedStatement updateStatement = connection.prepareStatement(query);

    updateStatement.setString(1, consultant.getFirstName());
    updateStatement.setString(2, consultant.getLastName());
    updateStatement.setString(3, consultant.getEmail());
    updateStatement.setString(4, consultant.getPhoneNumber());
    updateStatement.setString(5, consultant.getId());

    int result = updateStatement.executeUpdate();
    updateStatement.close();
    connection.close();

    return result > 0;
  }

  @Override
  public boolean delete(String id) throws SQLException, ClassNotFoundException {
    Connection connection = new DatabaseFactory().getDatabase(DatabaseType.MYSQL).getConnection();
    String query = "DELETE FROM consultant WHERE id = ?;";
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
      List<ConsultantSpecialization> consultantSpecializations = new ArrayList<>();
      ConsultantSpecialization consultantSpecialization = new ConsultantSpecialization(
          result.getString("specialization_id"), result.getString("country"), result.getString("job"));
      consultantSpecializations.add(consultantSpecialization);

      List<ConsultantAvailability> consultantAvailabilities = new ArrayList<>();
      ConsultantAvailability consultantAvailability = new ConsultantAvailability(result.getString("availability_id"),
          Day.valueOf(result.getString("day")), result.getString("start_time"), result.getString("end_time"));
      consultantAvailabilities.add(consultantAvailability);

      consultant = new Consultant(result.getString("id"), result.getString("email"), result.getString("phone_number"),
          result.getString("firstName"), result.getString("lastName"),
          consultantAvailabilities, consultantSpecializations);
    }

    result.close();
    findStatement.close();
    connection.close();

    return consultant;
  }

  @Override
  public List<Consultant> findMany() throws ClassNotFoundException, SQLException {
    Connection connection = new DatabaseFactory().getDatabase(DatabaseType.MYSQL).getConnection();
    String storedProcedure = "call FindConsultants()";
    CallableStatement findStatement = connection.prepareCall(storedProcedure);

    ResultSet result = findStatement.executeQuery(storedProcedure);
    List<Consultant> consultantList = new ArrayList<>();
    while (result.next()) {
      List<ConsultantSpecialization> consultantSpecializations = new ArrayList<>();
      ConsultantSpecialization consultantSpecialization = new ConsultantSpecialization(
          result.getString("specialization_id"), result.getString("country"), result.getString("job"));
      consultantSpecializations.add(consultantSpecialization);

      List<ConsultantAvailability> consultantAvailabilities = new ArrayList<>();
      ConsultantAvailability consultantAvailability = new ConsultantAvailability(result.getString("availability_id"),
          Day.valueOf(result.getString("day")), result.getString("start_time"), result.getString("end_time"));
      consultantAvailabilities.add(consultantAvailability);

      Consultant consultant = new Consultant(result.getString("id"), result.getString("email"),
          result.getString("phone_number"),
          result.getString("firstName"), result.getString("lastName"),
          consultantAvailabilities, consultantSpecializations);
      consultantList.add(consultant);
    }

    result.close();
    findStatement.close();
    connection.close();

    return consultantList;
  }

}
