package com.pintoss.gitftmall.domain.product.application;

import com.pintoss.gitftmall.common.exceptions.product.InvalidCategoryException;
import com.pintoss.gitftmall.domain.product.controller.response.ProductListResponse;
import com.pintoss.gitftmall.domain.product.mapper.ProductMapper;
import com.pintoss.gitftmall.domain.product.model.Product;
import com.pintoss.gitftmall.domain.product.model.value.ProductCategory;
import com.pintoss.gitftmall.domain.product.repository.IProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final IProductRepository productRepository;
    private final ProductMapper productMapper;

    public List<ProductListResponse> getAllProducts(Pageable pageable) {
        Page<Product> productPage = productRepository.findAll(pageable);

        return productMapper.toProductListResponseList(productPage);
    }

    public List<ProductListResponse> getPopularProducts(Pageable pageable) {
        Page<Product> productPage = productRepository.findByIsPopularTrue(pageable);

        return productMapper.toProductListResponseList(productPage);
    }

    public List<ProductListResponse> getProductsByCategory(String category, Pageable pageable) {
        ProductCategory productCategory = ProductCategory.from(category);

        Page<Product> productPage = productRepository.findByCategory(productCategory, pageable);

        return productMapper.toProductListResponseList(productPage);
    }
}
