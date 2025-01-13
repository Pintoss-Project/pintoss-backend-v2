package com.pintoss.gitftmall.domain.product.repository;

import com.pintoss.gitftmall.domain.product.model.Product;

public interface IProductRepository {

    void save(Product product);

    boolean existsByName(String name);
}
