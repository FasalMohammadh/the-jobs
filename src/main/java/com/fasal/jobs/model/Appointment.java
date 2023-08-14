package com.fasal.jobs.model;

import java.sql.Date;

import com.fasal.jobs.enums.AppointmentStatus;

public class Appointment {
	private int id;
	private String consultantId, jobSeekerId, country, job;
	private Date dateTime, createdAt;
	private AppointmentStatus status;

	public Appointment(int id, Date dateTime, String consultantId, String jobSeekerId, String country, String job,
			Date createdAt, AppointmentStatus status) {
		this.id = id;
		this.dateTime = dateTime;
		this.consultantId = consultantId;
		this.jobSeekerId = jobSeekerId;
		this.country = country;
		this.job = job;
		this.createdAt = createdAt;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getJobSeekerId() {
		return jobSeekerId;
	}

	public void setJobSeekerId(String jobSeekerId) {
		this.jobSeekerId = jobSeekerId;
	}

	public String getConsultantId() {
		return consultantId;
	}

	public void setConsultantId(String consultantId) {
		this.consultantId = consultantId;
	}

	public AppointmentStatus getStatus() {
		return status;
	}

	public void setStatus(AppointmentStatus status) {
		this.status = status;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

}
