package com.pintoss.gitftmall.domain.voucher.application;

import com.pintoss.gitftmall.domain.voucher.controller.response.ProductListResponse;
import com.pintoss.gitftmall.domain.voucher.mapper.ProductMapper;
import com.pintoss.gitftmall.domain.voucher.model.VoucherProvider;
import com.pintoss.gitftmall.domain.voucher.model.value.VoucherProviderCategory;
import com.pintoss.gitftmall.domain.voucher.repository.IVoucherProviderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final IVoucherProviderRepository productRepository;
    private final ProductMapper productMapper;

    public Page<ProductListResponse> getAllProducts(Pageable pageable) {
        Page<VoucherProvider> productPage = productRepository.findAll(pageable);

        return productMapper.toProductListResponseList(productPage);
    }

    public Page<ProductListResponse> getPopularProducts(Pageable pageable) {
        Page<VoucherProvider> productPage = productRepository.findByIsPopularTrue(pageable);

        return productMapper.toProductListResponseList(productPage);
    }

    public Page<ProductListResponse> getProductsByCategory(String category, Pageable pageable) {
        VoucherProviderCategory productCategory = VoucherProviderCategory.from(category);

        Page<VoucherProvider> productPage = productRepository.findByCategory(productCategory, pageable);

        return productMapper.toProductListResponseList(productPage);
    }
}
