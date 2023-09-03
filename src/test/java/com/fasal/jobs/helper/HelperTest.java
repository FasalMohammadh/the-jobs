package com.fasal.jobs.helper;

import com.fasal.jobs.util.helper.Helper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HelperTest {
  Helper helper;

  @BeforeEach
  void beforeEachMethod() {
    helper = new Helper();
  }

  @Test
  void testToLocalDateTime() {
    LocalDateTime localDateTime = helper.toLocalDateTime("2022-01-01", "12:00");
    assertEquals("2022-01-01T12:00", localDateTime.toString());
  }

  @Test
  void testToEncrypted() throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
    String text = "hello";
    String encrypted = helper.toEncrypted(text);

    assertEquals(text, helper.toDecrypted(encrypted));
  }
}
