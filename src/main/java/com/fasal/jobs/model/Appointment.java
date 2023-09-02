package com.fasal.jobs.model;

import java.sql.Date;
import java.time.LocalDateTime;

import com.fasal.jobs.enums.AppointmentStatus;

public class Appointment {
  private String id;
  private String consultantId;
  private String jobSeekerId;
  private String country;
  private String job;
  private LocalDateTime dateTime;
  private Date createdAt;
  private AppointmentStatus status;

  public Appointment(String id, LocalDateTime dateTime, String consultantId, String jobSeekerId, String country, String job,
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

  public String getId() {
    return id;
  }

  public void setId(String id) {
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

  public LocalDateTime getDateTime() {
    return dateTime;
  }

  public void setDateTime(LocalDateTime dateTime) {
    this.dateTime = dateTime;
  }

}
