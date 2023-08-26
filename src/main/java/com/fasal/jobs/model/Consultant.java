package com.fasal.jobs.model;

import java.util.List;
import java.time.LocalDate;

public class Consultant extends Person {
  private String id;
  private String email;
  private String phoneNumber;
  private LocalDate createdAt;
  private List<ConsultantAvailability> availability;
  private List<ConsultantSpecialization> specialization;

  public Consultant(String id, String email, String phoneNumber, String firstName, String lastName,
      List<ConsultantAvailability> availability, List<ConsultantSpecialization> specialization) {
    super(firstName, lastName);

    this.id = id;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.availability = availability;
    this.specialization = specialization;

  }

  public Consultant(String id, String email, String phoneNumber, String firstName, String lastName, LocalDate createdAt,
      List<ConsultantAvailability> availability, List<ConsultantSpecialization> specialization) {
    super(firstName, lastName);

    this.id = id;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.createdAt = createdAt;
    this.availability = availability;
    this.specialization = specialization;
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

  public LocalDate getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDate createdAt) {
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
