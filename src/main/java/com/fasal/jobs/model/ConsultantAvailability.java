package com.fasal.jobs.model;

import com.fasal.jobs.enums.Day;

public class ConsultantAvailability {
  private String id;
  private Day day;
  private String startTime;
  private String endTime;
  private String consultantId;

  public ConsultantAvailability(String id, Day day, String startTime, String endTime, String consultantId) {
    this.id = id;
    this.day = day;
    this.startTime = startTime;
    this.endTime = endTime;
    this.consultantId = consultantId;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Day getDay() {
    return day;
  }

  public void setDay(Day day) {
    this.day = day;
  }

  public String getStartTime() {
    return startTime;
  }

  public void setStartTime(String startTime) {
    this.startTime = startTime;
  }

  public String getEndTime() {
    return endTime;
  }

  public void setEndTime(String endTime) {
    this.endTime = endTime;
  }

  public String getConsultantId() {
    return consultantId;
  }

  public void setConsultantId(String consultantId) {
    this.consultantId = consultantId;
  }
}
