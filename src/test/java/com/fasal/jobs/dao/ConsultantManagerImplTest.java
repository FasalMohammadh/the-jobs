package com.fasal.jobs.dao;

import com.fasal.jobs.enums.Day;
import com.fasal.jobs.model.Consultant;
import com.fasal.jobs.model.ConsultantAvailability;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ConsultantManagerImplTest {
  ConsultantManager consultantManager;
  Consultant consultant;

  @BeforeEach
  void setUp() {
    consultantManager = new ConsultantManagerImpl();
    String consultantId = UUID.randomUUID().toString();

    ConsultantAvailability availability = new ConsultantAvailability("test", Day.FRI, "10.00", "11.00", consultantId);
    List<ConsultantAvailability> availabilityList = new ArrayList<>();
    availabilityList.add(availability);

    consultant = new Consultant(consultantId, "john@example.com", "123-456-7890", "John", "Doe", availabilityList,
        "USA", "Developer");
  }

  @Test
  @Disabled
  @DisplayName("Testing whether the consultant got created successfully")
  void consultantCreationTest() throws SQLException, ClassNotFoundException {
    boolean isCreated = consultantManager.create(consultant);

    assertTrue(isCreated);
    if (isCreated)
      consultantManager.delete(consultant.getId()); // deletes the created entry
  }

  @Test
  @Disabled
  @DisplayName("Testing whether the method throw NullPointerException when null consultant is passed")
  void consultantCreationTestNullConsultant() {
    assertThrows(NullPointerException.class, () -> {
      consultantManager.create(null);
    });
  }

  @Test
  @Disabled
  @DisplayName("Testing whether the method throw SQLException when consultant id is duplicated")
  void consultantCreationTestDuplicatedId() throws SQLException, ClassNotFoundException {
    boolean isFirstCreated = consultantManager.create(consultant);

    if (isFirstCreated) {
      assertThrows(SQLException.class, () -> {
        consultantManager.create(consultant);
      });

      consultantManager.delete(consultant.getId()); // deletes the created entry
    } else {
      fail("First consultant creation failed.");
    }
  }

  @Test
  @Disabled
  @DisplayName("Testing whether the consultant got updated successfully")
  void consultantUpdateTest() throws SQLException, ClassNotFoundException {
    boolean isFirstCreated = consultantManager.create(consultant);

    if (isFirstCreated) {
      boolean isUpdated = consultantManager.update(consultant);
      assertTrue(isUpdated);

      consultantManager.delete(consultant.getId()); // deletes the created entry
    } else {
      fail("Consultant creation failed (isFirstCreated). not updating");
    }
  }

  @Test
  @Disabled
  @DisplayName("Testing whether the update method throw NullPointerException when null consultant is passed")
  void consultantUpdateTestNullConsultant() {
    assertThrows(NullPointerException.class, () -> {
      consultantManager.update(null);
    });
  }

  @Test
  @Disabled
  @DisplayName("Testing whether the consultant got deleted successfully")
  void consultantDeleteTest() throws SQLException, ClassNotFoundException {
    boolean isFirstCreated = consultantManager.create(consultant);

    if (isFirstCreated) {
      boolean isDeleted = consultantManager.delete(consultant.getId());
      assertTrue(isDeleted);
    } else {
      fail("First consultant creation failed.");
    }
  }

  @Test
  @Disabled
  @DisplayName("Testing whether the consultant deletion fails when an invalid id is given")
  void consultantDeleteTestInvalidId() throws SQLException, ClassNotFoundException {
    boolean isDeleted = consultantManager.delete(UUID.randomUUID().toString() + UUID.randomUUID().toString());

    assertFalse(isDeleted, "deletion method provided invalid result on invalid id");
  }

  @Test
  @Disabled
  @DisplayName("Testing whether the consultant found with unique id.")
  public void findUniqueConsultantTestIsFound() throws SQLException, ClassNotFoundException {
    boolean isFirstCreated = consultantManager.create(consultant);

    if (isFirstCreated) {
      Consultant testConsultant = consultantManager.findUnique(consultant.getId());
      assertNotNull(testConsultant);
      consultantManager.delete(consultant.getId()); // deletes the created entry

    } else {
      fail("First consultant creation failed.");
    }

  }

  @Test
  @Disabled
  @DisplayName("Testing whether the consultant found with unique id is correctly assigned to model.")
  public void findUniqueTest() throws SQLException, ClassNotFoundException {
    boolean isFirstCreated = consultantManager.create(consultant);

    if (isFirstCreated) {
      Consultant testConsultant = consultantManager.findUnique(consultant.getId());

      assertNotNull(testConsultant);
      assertEquals(consultant.getId(), testConsultant.getId());
      assertEquals(consultant.getEmail(), testConsultant.getEmail());
      assertEquals(consultant.getPhoneNumber(), testConsultant.getPhoneNumber());
      assertEquals(consultant.getFirstName(), testConsultant.getFirstName());
      assertEquals(consultant.getLastName(), testConsultant.getLastName());
      assertNotNull(testConsultant.getCreatedAt());
      assertEquals(consultant.getCountry(), testConsultant.getCountry());
      assertEquals(consultant.getJob(), testConsultant.getJob());

      consultantManager.delete(consultant.getId()); // deletes the created entry

    } else {
      fail("First consultant creation failed. (isFirstCreated)");
    }

  }
}