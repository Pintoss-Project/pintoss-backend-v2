package com.pintoss.gitftmall.infra.persistence.product;

import com.pintoss.gitftmall.domain.product.controller.request.ProductSearchRequest;
import com.pintoss.gitftmall.domain.product.controller.response.ProductSearchResponse;
import com.pintoss.gitftmall.domain.product.repository.IProductQueryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProductQueryRepository implements IProductQueryRepository {

    private final ProductJpaRepository productJpaRepository;
    private final ProductQueryDslRepository productQueryDslRepository;

    @Override
    public boolean existsByName(String name) {
        return productJpaRepository.existsByName(name);
    }

    @Override
    public Page<ProductSearchResponse> search(ProductSearchRequest keyword, Pageable pageable) {
        return productQueryDslRepository.search(keyword, pageable);
    }
}
