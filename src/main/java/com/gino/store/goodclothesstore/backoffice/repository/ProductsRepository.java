package com.gino.store.goodclothesstore.backoffice.repository;

import com.gino.store.goodclothesstore.backoffice.model.Product;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductsRepository extends PagingAndSortingRepository<Product, Long> {

  Product save(Product product);
}
