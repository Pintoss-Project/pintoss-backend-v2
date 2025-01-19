package com.pintoss.gitftmall.infra.persistence.product;

import com.pintoss.gitftmall.domain.product.model.Product;
import com.pintoss.gitftmall.domain.product.model.value.ProductCategory;
import com.pintoss.gitftmall.domain.product.repository.IProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements IProductRepository {

    private final ProductJpaRepository productJpaRepository;

    @Override
    public void save(Product product) {
        productJpaRepository.save(product);
    }

    @Override
    public boolean existsByName(String name) {
        return productJpaRepository.existsByName(name);
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productJpaRepository.findAll(pageable);
    }

    @Override
    public Page<Product> findByIsPopularTrue(Pageable pageable) {
        return productJpaRepository.findByIsPopularTrue(pageable);
    }

    @Override
    public Page<Product> findByCategory(ProductCategory category, Pageable pageable) {
        return productJpaRepository.findByCategory(category, pageable);
    }

}
