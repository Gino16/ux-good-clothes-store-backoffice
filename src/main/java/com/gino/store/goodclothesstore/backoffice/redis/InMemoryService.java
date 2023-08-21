package com.gino.store.goodclothesstore.backoffice.redis;

import reactor.core.publisher.Mono;

public interface InMemoryService {
  public Mono<Boolean> setObject(String key, Object value);

  public <T> Mono<T>  getObject(String key, Class<T> source);

  public Mono<Boolean> deleteObject(String key);
  public Mono<Boolean> hasKey(String key);
}
