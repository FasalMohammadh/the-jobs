package com.fasal.jobs.util.helper;

import com.fasal.jobs.enums.SessionUser;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpSession;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

public class Helper {
  private static Helper helper;

  public static synchronized Helper getHelper() {
    if (helper == null)
      helper = new Helper();

    return helper;
  }

  public LocalDateTime toLocalDateTime(String date, String time) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    return LocalDateTime.parse(date + " " + time, formatter);
  }

  public String toJsDateTime(LocalDate date) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    return formatter.format(date);
  }

  public String toEncrypted(String decodedString) throws NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
    Cipher cipher = Cipher.getInstance("AES");
    SecretKey secretKey = new SecretKeySpec("489e77fe7d775e49e843126788775de7".getBytes(), "AES");
    cipher.init(Cipher.ENCRYPT_MODE, secretKey);
    byte[] hashedPassword = cipher.doFinal(decodedString.getBytes());
    return Base64.getEncoder().encodeToString(hashedPassword);
  }

  public String toDecrypted(String encodeString) throws NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
    Cipher cipher = Cipher.getInstance("AES");
    SecretKey secretKey = new SecretKeySpec("489e77fe7d775e49e843126788775de7".getBytes(), "AES");
    cipher.init(Cipher.DECRYPT_MODE, secretKey);
    byte[] plainText = cipher.doFinal(Base64.getDecoder().decode(encodeString));
    return new String(plainText);
  }

  public void setUserSession(HttpSession session, SessionUser type, String email) {
    session.setAttribute("type", type.toString());
    session.setAttribute("email", email);
  }

  public void removeUserSession(HttpSession session) {
    session.removeAttribute("type");
    session.removeAttribute("email");
  }

  public boolean isLoggedAs(HttpSession session, SessionUser type) {
    String sessionUser = (String) session.getAttribute("type");
    if (sessionUser == null) return false;

    if (SessionUser.valueOf(sessionUser).equals(type))
      return true;

    return false;

  }

//  public <T> List<T> differnce(List<T> list , List<T> list2, BinaryOperator<boolean> compare = (T a, T b) -> boolean){
//    for(T item:list){
//      for(T item2:list2){
//
//      }
//    }
//  }
}
