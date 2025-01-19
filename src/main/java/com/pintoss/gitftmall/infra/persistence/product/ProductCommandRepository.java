package com.pintoss.gitftmall.infra.persistence.product;

import com.pintoss.gitftmall.domain.product.model.Product;
import com.pintoss.gitftmall.domain.product.repository.IProductCommandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProductCommandRepository implements IProductCommandRepository {

    private final ProductJpaRepository productJpaRepository;
    // query dsl을 이용한 repository

    @Override
    public void save(Product product) {
        productJpaRepository.save(product);
    }
}
