package com.gino.store.goodclothesstore.backoffice.mapper.impl;

import com.gino.store.backoffice.model.ProductRequest;
import com.gino.store.goodclothesstore.backoffice.mapper.ProductsMapper;
import com.gino.store.goodclothesstore.backoffice.model.Product;
import com.gino.store.goodclothesstore.backoffice.model.Tag;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class ProductsMapperImpl implements ProductsMapper {

  @Override
  public Product toProduct(ProductRequest productRequest) {
    return Product.builder()
        .title(productRequest.getTitle())
        .description(productRequest.getDescription())
        .tags(productRequest.getTags().stream()
            .map(tag -> Tag.builder().id(tag.getId()).build())
            .collect(Collectors.toSet()))
        .build();
  }
}
