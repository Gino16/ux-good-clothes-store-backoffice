package com.gino.store.goodclothesstore.backoffice.util;

import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gino.store.backoffice.model.Metadata;
import com.gino.store.goodclothesstore.backoffice.model.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public class Utils {

  private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper()
      .configure(Feature.ALLOW_SINGLE_QUOTES, true);

  public static Metadata buildMetadata(Page<Object> elements) {
    return Metadata.builder()
        .limit(elements.getNumberOfElements())
        .offset((int) elements.getPageable().getOffset())
        .totalRecords(elements.getContent().size())
        .build();
  }
  public static PageRequest buildPageRequest(Integer limit, Integer offset) {
    return PageRequest.of(
        calculatePage(limit, offset),
        limit);
  }

  private static int calculatePage(Integer limit, Integer offset) {
    return (offset / limit) + 1;
  }


  public static String objectToJson(Object object) {
    try {
      return OBJECT_MAPPER.writeValueAsString(object);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public static <T> T jsonToObject(String value, Class<T> source) {
    try {
      return OBJECT_MAPPER.readValue(value, source);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }

}
