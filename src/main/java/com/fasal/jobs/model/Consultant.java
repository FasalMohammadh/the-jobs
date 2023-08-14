package com.fasal.jobs.model;

import java.util.List;

public class Consultant extends Person {
  private int id;
  private String email;
  private String phoneNumber;
  private String createdAt;
  private List<ConsultantAvailability> availability;
  private List<ConsultantSpecialization> specialization;

  public Consultant(int id, String email, String phoneNumber, String createdAt,
      List<ConsultantAvailability> availability, List<ConsultantSpecialization> specialization) {
    this.id = id;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.createdAt = createdAt;
    this.availability = availability;
    this.specialization = specialization;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
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

  public List<ConsultantSpecialization> getSpecialization() {
    return specialization;
  }

  public void setSpecialization(List<ConsultantSpecialization> specialization) {
    this.specialization = specialization;
  }

}
