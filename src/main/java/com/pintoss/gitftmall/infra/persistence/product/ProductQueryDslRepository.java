package com.pintoss.gitftmall.infra.persistence.product;

import com.pintoss.gitftmall.domain.product.controller.request.ProductSearchRequest;
import com.pintoss.gitftmall.domain.product.controller.response.ProductSearchResponse;
import com.pintoss.gitftmall.domain.product.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface ProductQueryDslRepository {
    Page<ProductSearchResponse> search(ProductSearchRequest keyword, Pageable pageable);

}
