package com.fasal.jobs.model;

public class ConsultantSpecialization {
  private int id;
  private String country;
  private String job;

  public ConsultantSpecialization(int id, String country, String job) {
    this.id = id;
    this.country = country;
    this.job = job;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
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
