package com.gino.store.goodclothesstore.backoffice.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Utils {
  private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

  public static String toJson(Object object) {
    try {
      return OBJECT_MAPPER.writeValueAsString(object);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
