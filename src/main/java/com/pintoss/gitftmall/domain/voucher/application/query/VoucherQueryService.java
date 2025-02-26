package com.pintoss.gitftmall.domain.voucher.application.query;

import com.pintoss.gitftmall.domain.voucher.controller.dto.VoucherDetailResponse;
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
