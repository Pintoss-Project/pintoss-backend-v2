package com.pintoss.gitftmall.domain.voucher.application;

import com.pintoss.gitftmall.domain.voucher.controller.response.VoucherDetailResponse;
import com.pintoss.gitftmall.domain.voucher.domain.repository.VoucherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VoucherQueryService {

    private final VoucherRepository voucherRepository;

    public List<VoucherDetailResponse> findByVoucherProviderId(Long providerId) {
        return voucherRepository.findByVoucherProviderId(providerId);
    }
}
