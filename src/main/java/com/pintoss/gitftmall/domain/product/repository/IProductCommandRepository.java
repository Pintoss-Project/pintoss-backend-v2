package com.pintoss.gitftmall.domain.product.repository;

import com.pintoss.gitftmall.domain.product.model.Product;

public interface IProductCommandRepository {

    void save(Product product);
}
