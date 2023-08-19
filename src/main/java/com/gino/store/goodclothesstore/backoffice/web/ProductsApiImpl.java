package com.gino.store.goodclothesstore.backoffice.web;

import com.gino.store.backoffice.api.ProductsApi;
import com.gino.store.backoffice.model.ProductResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Controller
@RequestMapping("/api/v1/products")
public class ProductsApiImpl implements ProductsApi {


}
