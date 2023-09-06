package com.fasal.jobs.dao;

import com.fasal.jobs.model.JobSeeker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;


class JobSeekerManagerImplTest {
  JobSeekerManager jobSeekerManager;
  JobSeeker jobSeeker;


  @BeforeEach
  void setUp() {
    jobSeekerManager = new JobSeekerManagerImpl();
    String jobSeekerId = UUID.randomUUID().toString();

    jobSeeker = new JobSeeker(jobSeekerId, UUID.randomUUID() + "@example.com", "123-456-78900", "John", "Doe");
  }

  @Test
  @DisplayName("Testing whether the jobseeker got created successfully")
  void jobSeekerCreationTest() throws SQLException, ClassNotFoundException {
    boolean isCreated = jobSeekerManager.create(jobSeeker);

    assertTrue(isCreated);
    if (isCreated)
      jobSeekerManager.delete(jobSeeker.getId()); // deletes the created entry
  }

  @Test
  @DisplayName("Testing whether the method throw NullPointerException when null jobSeeker is passed")
  void jobSeekerCreationTestNullJobSeeker() {
    assertThrows(NullPointerException.class, () -> {
      jobSeekerManager.create(null);
    });
  }


  @Test
  @DisplayName("Testing whether the method throw SQLException when Jobseeker id is duplicated")
  void jobSeekerCreationTestDuplicatedId() throws SQLException, ClassNotFoundException {
    boolean isFirstCreated = jobSeekerManager.create(jobSeeker);

    if (isFirstCreated) {
      assertThrows(SQLException.class, () -> {
        jobSeekerManager.create(jobSeeker);
      });

      jobSeekerManager.delete(jobSeeker.getId()); // deletes the created entry
    } else {
      fail("First jobSeeker creation failed.");
    }
  }


  @Test
  @DisplayName("Testing whether the jobSeeker got updated successfully")
  void jobSeekerUpdateTest() throws SQLException, ClassNotFoundException {
    boolean isFirstCreated = jobSeekerManager.create(jobSeeker);

    if (isFirstCreated) {
      boolean isUpdated = jobSeekerManager.update(jobSeeker);
      assertTrue(isUpdated);

      jobSeekerManager.delete(jobSeeker.getId()); // deletes the created entry
    } else {
      fail("JobSeeker creation failed (isFirstCreated). not updating");
    }
  }

  @Test
  @DisplayName("Testing whether the update method throw NullPointerException when null jobSeeker is passed")
  void jobSeekerUpdateTestNullJobSeeker() {
    assertThrows(NullPointerException.class, () -> {
      jobSeekerManager.update(null);
    });
  }

  @Test
  @DisplayName("Testing whether the jobSeeker got deleted successfully")
  void jobSeekerDeleteTest() throws SQLException, ClassNotFoundException {
    boolean isFirstCreated = jobSeekerManager.create(jobSeeker);

    if (isFirstCreated) {
      boolean isDeleted = jobSeekerManager.delete(jobSeeker.getId());
      assertTrue(isDeleted);
    } else {
      fail("First jobSeeker creation failed.");
    }
  }

  @Test
  @DisplayName("Testing whether the jobSeeker deletion fails when an invalid id is given")
  void jobSeekerDeleteTestInvalidId() throws SQLException, ClassNotFoundException {
    boolean isDeleted = jobSeekerManager.delete(UUID.randomUUID().toString() + UUID.randomUUID().toString());

    assertFalse(isDeleted, "deletion method provided invalid result on invalid id");
  }

  @Test
  @DisplayName("Testing whether the jobSeeker found with unique id.")
  public void findUniqueJobSeekerTestIsFound() throws SQLException, ClassNotFoundException {
    boolean isFirstCreated = jobSeekerManager.create(jobSeeker);

    if (isFirstCreated) {
      JobSeeker testJobSeeker = jobSeekerManager.findUnique(jobSeeker.getId());
      assertNotNull(testJobSeeker);
      jobSeekerManager.delete(jobSeeker.getId());
    } else {
      fail("First jobSeeker creation failed.");
    }

  }

  @Test
  @DisplayName("Testing whether the jobSeeker found with unique id is correctly assigned to model.")
  public void findUniqueTest() throws SQLException, ClassNotFoundException {
    boolean isFirstCreated = jobSeekerManager.create(jobSeeker);

    if (isFirstCreated) {
      JobSeeker testJobSeeker = jobSeekerManager.findUnique(jobSeeker.getId());

      assertNotNull(testJobSeeker);
      assertEquals(jobSeeker.getId(), testJobSeeker.getId());
      assertEquals(jobSeeker.getEmail(), testJobSeeker.getEmail());
      assertEquals(jobSeeker.getPhoneNumber(), testJobSeeker.getPhoneNumber());
      assertEquals(jobSeeker.getFirstName(), testJobSeeker.getFirstName());
      assertEquals(jobSeeker.getLastName(), testJobSeeker.getLastName());
      assertNotNull(testJobSeeker.getCreatedAt());

      jobSeekerManager.delete(jobSeeker.getId());
    } else {
      fail("First jobSeeker creation failed. (isFirstCreated)");
    }

  }
}