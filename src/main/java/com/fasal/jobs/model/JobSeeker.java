package com.fasal.jobs.model;

public class JobSeeker extends Person {
  private String id;
  private String email;
  private String phoneNumber;
  private String createdAt;

  private String password;

  public JobSeeker(String id, String email, String phoneNumber, String firstName, String lastName, String createdAt) {
    super(firstName, lastName);

    this.id = id;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.createdAt = createdAt;
  }

  public JobSeeker(String id, String email, String phoneNumber, String firstName, String lastName) {
    super(firstName, lastName);

    this.id = id;
    this.email = email;
    this.phoneNumber = phoneNumber;
  }

  public JobSeeker(String id, String email, String phoneNumber, String firstName, String lastName, String password, String createdAt) {
    super(firstName, lastName);

    this.id = id;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.password = password;
    this.createdAt = createdAt;
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

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
