package com.fasal.jobs.dao;

import com.fasal.jobs.enums.AppointmentStatus;
import com.fasal.jobs.enums.DatabaseType;
import com.fasal.jobs.model.Appointment;
import com.fasal.jobs.util.database.DatabaseFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AppointmentManagerImpl implements AppointmentManager {

	@Override
	public boolean create(Appointment appointment) throws SQLException, ClassNotFoundException {
		Connection connection = new DatabaseFactory().getDatabase(DatabaseType.MYSQL).getConnection();
		String query = "INSERT INTO appointment(date_time, job, country, job_seeker_id, consultant_id, status) VALUES(?,?,?,?,?,?);";
		PreparedStatement createStatement = connection.prepareStatement(query);

		createStatement.setDate(1, appointment.getDateTime());
		createStatement.setString(2, appointment.getJob());
		createStatement.setString(3, appointment.getCountry());
		createStatement.setString(4, appointment.getJobSeekerId());
		createStatement.setString(5, appointment.getConsultantId());
		createStatement.setString(6, appointment.getStatus().toString());

		int result = createStatement.executeUpdate();
		createStatement.close();
		connection.close();

		return result > 0;
	}

	@Override
	public boolean update(Appointment appointment) throws ClassNotFoundException, SQLException {
		Connection connection = new DatabaseFactory().getDatabase(DatabaseType.MYSQL).getConnection();
		String query = "UPDATE appointment SET date_time=?, job=?, country=?, job_seeker_id=?, consultant_id=?, status=?  WHERE id=?";
		PreparedStatement updateStatement = connection.prepareStatement(query);

		updateStatement.setDate(1, appointment.getDateTime());
		updateStatement.setString(2, appointment.getJob());
		updateStatement.setString(3, appointment.getCountry());
		updateStatement.setString(4, appointment.getJobSeekerId());
		updateStatement.setString(5, appointment.getConsultantId());
		updateStatement.setString(6, appointment.getStatus().toString());

		int result = updateStatement.executeUpdate();
		updateStatement.close();
		connection.close();

		return result > 0;
	}

	@Override
	public boolean delete(String id) throws SQLException, ClassNotFoundException {
		Connection connection = new DatabaseFactory().getDatabase(DatabaseType.MYSQL).getConnection();
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
		Connection connection = new DatabaseFactory().getDatabase(DatabaseType.MYSQL).getConnection();
		String query = "SELECT * FROM appointment WHERE id=?";
		PreparedStatement findStatement = connection.prepareStatement(query);

		findStatement.setString(1, appointmentId);
		ResultSet result = findStatement.executeQuery();

		Appointment appointment = null;
		if (result.next())
			appointment = new Appointment(
					result.getString("id"), result.getDate("date_time"), result.getString("consultant_id"),
					result.getString("job_seeker_id"),
					result.getString("country"), result.getString("job"),
					result.getDate("createdAt"), AppointmentStatus.valueOf(result.getString("status")));

		result.close();
		findStatement.close();
		connection.close();

		return appointment;
	}

	@Override
	public List<Appointment> findMany() throws ClassNotFoundException, SQLException {
		Connection connection = new DatabaseFactory().getDatabase(DatabaseType.MYSQL).getConnection();
		String query = "SELECT * FROM appointment";
		Statement findStatement = connection.createStatement();

		ResultSet result = findStatement.executeQuery(query);
		List<Appointment> appointmentList = new ArrayList<Appointment>();
		while (result.next()) {
			Appointment appointment = new Appointment(
					result.getString("id"), result.getDate("date_time"), result.getString("consultant_id"),
					result.getString("job_seeker_id"),
					result.getString("country"), result.getString("job"),
					result.getDate("createdAt"), AppointmentStatus.valueOf(result.getString("status")));

			appointmentList.add(appointment);
		}

		result.close();
		findStatement.close();
		connection.close();

		return appointmentList;
	}

	public boolean updateAppointmentStatus(Appointment appointment) throws SQLException, ClassNotFoundException {
		Connection connection = new DatabaseFactory().getDatabase(DatabaseType.MYSQL).getConnection();
		String query = "UPDATE appointment SET status=? WHERE id=?";
		PreparedStatement updateStatement = connection.prepareStatement(query);

		updateStatement.setString(1, appointment.getStatus().toString());
		updateStatement.setString(2, appointment.getId());

		int result = updateStatement.executeUpdate();
		updateStatement.close();
		connection.close();

		return result > 0;
	}
}
