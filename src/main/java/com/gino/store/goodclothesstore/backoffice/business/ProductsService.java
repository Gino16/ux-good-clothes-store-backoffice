package com.gino.store.goodclothesstore.backoffice.business;

import com.gino.store.backoffice.model.ProductRequest;
import reactor.core.publisher.Mono;

public interface ProductsService {
  Mono<Void> addProduct(String xAuthToken, Mono<ProductRequest> productRequest);
}
