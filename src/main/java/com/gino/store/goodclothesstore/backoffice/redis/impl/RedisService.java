package com.gino.store.goodclothesstore.backoffice.redis.impl;

import com.gino.store.goodclothesstore.backoffice.redis.InMemoryService;
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
    return reactiveRedisTemplate.opsForValue().setIfAbsent(key, value);
  }

  @Override
  public Mono<Object> getObject(String key) {
    return reactiveRedisTemplate.opsForValue().get(key);
  }

  @Override
  public Mono<Boolean> deleteObject(String key) {
    return reactiveRedisTemplate.opsForValue().delete(key);
  }
}
