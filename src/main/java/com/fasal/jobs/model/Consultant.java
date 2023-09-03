package com.fasal.jobs.model;

import java.util.List;

public class Consultant extends Person {
  private String id;
  private String email;
  private String phoneNumber;
  private String country;
  private String job;
  private String createdAt;
  private List<ConsultantAvailability> availability;

  public Consultant(String id, String email, String phoneNumber, String firstName, String lastName,
                    List<ConsultantAvailability> availability, String country, String job) {
    super(firstName, lastName);

    this.id = id;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.availability = availability;
    this.country = country;
    this.job = job;
  }

  public Consultant(String id, String email, String phoneNumber, String firstName, String lastName, String createdAt,
                    List<ConsultantAvailability> availability, String country, String job) {
    super(firstName, lastName);

    this.id = id;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.createdAt = createdAt;
    this.availability = availability;
    this.country = country;
    this.job = job;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }

  public List<ConsultantAvailability> getAvailability() {
    return availability;
  }

  public void setAvailability(List<ConsultantAvailability> availability) {
    this.availability = availability;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getJob() {
    return job;
  }

  public void setJob(String job) {
    this.job = job;
  }
}
