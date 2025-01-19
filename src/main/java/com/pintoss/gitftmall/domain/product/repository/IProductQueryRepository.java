package com.pintoss.gitftmall.domain.product.repository;

import com.pintoss.gitftmall.domain.product.controller.request.ProductSearchRequest;
import com.pintoss.gitftmall.domain.product.controller.response.ProductSearchResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface IProductQueryRepository {
    boolean existsByName(String name);

    Page<ProductSearchResponse> search(ProductSearchRequest request, Pageable pageable);
}
