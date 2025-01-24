package com.pintoss.gitftmall.domain.voucher.application;

import com.pintoss.gitftmall.common.exceptions.ErrorCode;
import com.pintoss.gitftmall.common.exceptions.voucher.InvalidVoucherException;
import com.pintoss.gitftmall.common.exceptions.voucher.InvalidVoucherProviderIdException;
import com.pintoss.gitftmall.domain.voucher.controller.request.VoucherStockUpdateRequest;
import com.pintoss.gitftmall.domain.voucher.controller.response.VoucherResponse;
import com.pintoss.gitftmall.domain.voucher.mapper.VoucherMapper;
import com.pintoss.gitftmall.domain.voucher.model.Voucher;
import com.pintoss.gitftmall.domain.voucher.model.VoucherProvider;
import com.pintoss.gitftmall.domain.voucher.repository.IVoucherProviderRepository;
import com.pintoss.gitftmall.domain.voucher.repository.IVoucherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VoucherService {

    private final IVoucherProviderRepository voucherProviderRepository;
    private final IVoucherRepository voucherRepository;
    private final VoucherMapper voucherMapper;

    public Page<VoucherResponse> getAll(Long providerId, Pageable pageable) {
        Optional<VoucherProvider> optionalVoucherProvider = voucherProviderRepository.findById(providerId);
        if(optionalVoucherProvider.isEmpty()) {
            throw new InvalidVoucherProviderIdException(ErrorCode.BAD_REQUEST, "해당 VoucherProvider ID는 존재하지 않습니다.");
        }
        VoucherProvider voucherProvider = optionalVoucherProvider.get();

        Page<Voucher> voucherPage = voucherRepository.findByVoucherProvider(voucherProvider, pageable);

        return voucherMapper.toVoucherResponsePage(voucherPage);
    }

    public void update(Long voucherId, VoucherStockUpdateRequest request) {
        Optional<Voucher> optionalVoucher = voucherRepository.findById(voucherId);
        if(optionalVoucher.isEmpty()) {
            throw new InvalidVoucherException(ErrorCode.BAD_REQUEST, "해당 Voucher ID는 존재하지 않습니다.");
        }
        Voucher voucher = optionalVoucher.get();

        voucher.updateStock(request.getStock());

        voucherRepository.save(voucher);
    }

    public void delete(Long voucherId) {
        Optional<Voucher> optionalVoucher = voucherRepository.findById(voucherId);
        if(optionalVoucher.isEmpty()) {
            throw new InvalidVoucherException(ErrorCode.BAD_REQUEST, "해당 Voucher ID는 존재하지 않습니다.");
        }
        Voucher voucher = optionalVoucher.get();

        voucherRepository.delete(voucher);
    }
}
