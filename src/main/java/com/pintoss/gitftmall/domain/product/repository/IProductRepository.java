package com.pintoss.gitftmall.domain.product.repository;

import com.pintoss.gitftmall.domain.product.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IProductRepository {

    void save(Product product);

    boolean existsByName(String name);

    Page<Product> getProducts(Pageable pageable);

    Page<Product> getPopularProducts(Pageable pageable);
}
