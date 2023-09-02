package com.gino.store.goodclothesstore.backoffice.web;

import com.gino.store.backoffice.api.ProductsApi;
import com.gino.store.backoffice.model.ProductRequest;
import com.gino.store.backoffice.model.ProductResponse;
import com.gino.store.goodclothesstore.backoffice.business.ProductsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Controller
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductsApiImpl implements ProductsApi {
  private final ProductsService productsService;
  @Override
  public Mono<ResponseEntity<Void>> addProduct(String xAuthToken,
      Mono<ProductRequest> productRequest, ServerWebExchange exchange) {
    return productsService.addProduct(xAuthToken, productRequest)
        .then(Mono.just(ResponseEntity.ok().build()));
  }
}
