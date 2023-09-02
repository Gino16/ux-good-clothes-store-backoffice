package com.gino.store.goodclothesstore.backoffice.business.impl;

import com.gino.store.backoffice.model.ProductRequest;
import com.gino.store.goodclothesstore.backoffice.business.ProductsService;
import com.gino.store.goodclothesstore.backoffice.mapper.ProductsMapper;
import com.gino.store.goodclothesstore.backoffice.repository.ProductsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
@Slf4j
public class ProductsServiceImpl implements ProductsService {

  private final ProductsRepository productsRepository;
  private final ProductsMapper productsMapper;

  @Override
  public Mono<Void> addProduct(String xAuthToken, Mono<ProductRequest> productRequest) {
    return productRequest.map(
            request -> productsRepository.save(productsMapper.toProduct(request)))
        .then();
  }
}
