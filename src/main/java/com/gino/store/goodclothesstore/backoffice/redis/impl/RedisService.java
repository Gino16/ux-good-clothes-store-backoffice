package com.gino.store.goodclothesstore.backoffice.redis.impl;

import static com.gino.store.goodclothesstore.backoffice.util.Utils.jsonToObject;
import static com.gino.store.goodclothesstore.backoffice.util.Utils.objectToJson;

import com.gino.store.goodclothesstore.backoffice.redis.InMemoryService;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class RedisService implements InMemoryService {

  private final ReactiveRedisTemplate<String, Object> reactiveRedisTemplate;

  @Override
  public Mono<Boolean> setObject(String key, Object value) {
    String jsonString = objectToJson(value);
    return reactiveRedisTemplate.opsForValue().set(key, jsonString);
  }

  @Override
  public <T> Mono<T> getObject(String key, Class<T> source) {
    return reactiveRedisTemplate.opsForValue().get(key)
        .filter(Objects::nonNull)
        .switchIfEmpty(Mono.just("{}"))
        .cast(String.class)
        .map(jsonString -> jsonToObject(jsonString, source));
  }

  @Override
  public Mono<Boolean> deleteObject(String key) {
    return reactiveRedisTemplate.opsForValue().delete(key);
  }

  @Override
  public Mono<Boolean> hasKey(String key) {
    return reactiveRedisTemplate.hasKey(key);
  }
}
