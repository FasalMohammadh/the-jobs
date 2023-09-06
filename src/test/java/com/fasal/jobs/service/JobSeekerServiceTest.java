package com.fasal.jobs.service;

import com.fasal.jobs.model.JobSeeker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class JobSeekerServiceTest {
  private JobSeekerService service;
  JobSeeker jobSeeker;

  @BeforeEach
  void setUp() {
    service = JobSeekerService.getService();
    jobSeeker = new JobSeeker(
            UUID.randomUUID().toString(),
            "example@gmail.com",
            "+94123123123",
            "fasal",
            "ressak");
  }


  @Test
  @DisplayName("Testing whether it return a JobSeekerService instance without null")
  void getServiceTest() {
    assertNotNull(service, "getService() in JobSeekerService returned null.");
  }

  @Test
  @DisplayName("Testing whether JobSeekerService.create() return true on valid jobseeker object")
  void createTestNullJobSeeker() throws SQLException, ClassNotFoundException {
    boolean isCreated=service.create(jobSeeker);
    assertTrue(isCreated,"JobSeekerService.create() returned false on valid jobseeker object");
  }

}