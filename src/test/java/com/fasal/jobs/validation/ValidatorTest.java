package com.fasal.jobs.validation;

import com.fasal.jobs.util.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ValidatorTest {

  Validator validator;

  @BeforeEach
  void beforeEach() {
    validator = new Validator();
  }

  @Test
  void testGetValidator() {
    Validator validator = Validator.getValidator();
    assertNotNull(validator);
  }

  @Test
  void testCheckEmailValidityFalse() {
    String email = "email";
    boolean isValidEmail = Validator.getValidator().checkEmailValidity(email);
    assertFalse(isValidEmail);
  }

  @Test
  void testCheckEmailValidityTrue() {
    String email = "fasalmohammadh@gmail.com";
    boolean isValidEmail = Validator.getValidator().checkEmailValidity(email);
    assertTrue(isValidEmail);
  }
}
