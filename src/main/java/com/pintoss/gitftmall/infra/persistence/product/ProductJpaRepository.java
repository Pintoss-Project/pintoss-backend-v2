package com.pintoss.gitftmall.infra.persistence.product;

import com.pintoss.gitftmall.domain.product.model.Product;
import com.pintoss.gitftmall.domain.product.model.value.ProductCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductJpaRepository extends JpaRepository<Product,Long> {
    boolean existsByName(String name);

    Page<Product> findByIsPopularTrue(Pageable pageable);

    Page<Product> findByCategory(ProductCategory category, Pageable pageable);
}
