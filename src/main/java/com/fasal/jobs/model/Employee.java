package com.fasal.jobs.model;

public class Employee extends Person {
  private int id;
  private String role;
  private String createdAt;
  private String email;

  public Employee(int id, String role, String createdAt, String email) {
    this.id = id;
    this.role = role;
    this.createdAt = createdAt;
    this.email = email;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
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
}
