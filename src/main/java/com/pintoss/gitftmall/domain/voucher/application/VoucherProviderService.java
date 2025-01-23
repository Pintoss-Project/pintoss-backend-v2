package com.pintoss.gitftmall.domain.voucher.application;

import com.pintoss.gitftmall.domain.voucher.controller.response.VoucherProviderResponse;
import com.pintoss.gitftmall.domain.voucher.mapper.VoucherProviderMapper;
import com.pintoss.gitftmall.domain.voucher.model.VoucherProvider;
import com.pintoss.gitftmall.domain.voucher.model.value.VoucherProviderCategory;
import com.pintoss.gitftmall.domain.voucher.repository.IVoucherProviderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VoucherProviderService {

    private final IVoucherProviderRepository voucherProviderRepository;
    private final VoucherProviderMapper voucherProviderMapper;

    public Page<VoucherProviderResponse> getAll(Pageable pageable) {
        Page<VoucherProvider> productPage = voucherProviderRepository.findAll(pageable);

        return voucherProviderMapper.toProductListResponseList(productPage);
    }

    public Page<VoucherProviderResponse> getPopular(Pageable pageable) {
        Page<VoucherProvider> productPage = voucherProviderRepository.findByIsPopularTrue(pageable);

        return voucherProviderMapper.toProductListResponseList(productPage);
    }

    public Page<VoucherProviderResponse> getByCategory(String category, Pageable pageable) {
        VoucherProviderCategory productCategory = VoucherProviderCategory.from(category);

        Page<VoucherProvider> productPage = voucherProviderRepository.findByCategory(productCategory, pageable);

        return voucherProviderMapper.toProductListResponseList(productPage);
    }
}
