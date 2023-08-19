package com.gino.store.goodclothesstore.backoffice.redis;

import reactor.core.publisher.Mono;

public interface InMemoryService {
  public Mono<Boolean> setObject(String key, Object value);

  public Mono<Object> getObject(String key);

  public Mono<Boolean> deleteObject(String key);

}
