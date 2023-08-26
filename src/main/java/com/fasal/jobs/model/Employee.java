package com.fasal.jobs.model;

public class Employee extends Person {
  private String id;
  private String role;
  private String createdAt;
  private String email;
  private String password;

  public Employee(String id, String firstName, String lastName, String email, String password, String role,
      String createdAt) {
    super(firstName, lastName);

    this.id = id;
    this.role = role;
    this.email = email;
    this.password = password;
    this.createdAt = createdAt;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
