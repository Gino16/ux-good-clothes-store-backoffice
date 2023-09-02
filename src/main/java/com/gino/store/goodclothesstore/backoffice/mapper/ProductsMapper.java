package com.gino.store.goodclothesstore.backoffice.mapper;

import com.gino.store.backoffice.model.ProductRequest;
import com.gino.store.goodclothesstore.backoffice.model.Product;

public interface ProductsMapper {
  Product toProduct(ProductRequest productRequest);
}
