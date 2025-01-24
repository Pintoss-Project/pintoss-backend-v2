package com.pintoss.gitftmall.domain.voucher.application;

import com.pintoss.gitftmall.common.exceptions.ErrorCode;
import com.pintoss.gitftmall.common.exceptions.voucher.DuplicateVoucherProviderNameException;
import com.pintoss.gitftmall.common.exceptions.voucher.InvalidVoucherProviderIdException;
import com.pintoss.gitftmall.domain.voucher.controller.request.VoucherProviderUpdateRequest;
import com.pintoss.gitftmall.domain.voucher.controller.response.VoucherProviderDetailResponse;
import com.pintoss.gitftmall.domain.voucher.controller.response.VoucherProviderResponse;
import com.pintoss.gitftmall.domain.voucher.mapper.VoucherProviderDetailMapper;
import com.pintoss.gitftmall.domain.voucher.mapper.VoucherProviderMapper;
import com.pintoss.gitftmall.domain.voucher.model.VoucherProvider;
import com.pintoss.gitftmall.domain.voucher.model.value.VoucherProviderCategory;
import com.pintoss.gitftmall.domain.voucher.repository.IVoucherProviderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VoucherProviderService {

    private final IVoucherProviderRepository voucherProviderRepository;
    private final VoucherProviderMapper voucherProviderMapper;
    private final VoucherProviderDetailMapper voucherProviderDetailMapper;

    public Page<VoucherProviderResponse> getAll(Pageable pageable) {
        Page<VoucherProvider> voucherProviderPage = voucherProviderRepository.findAll(pageable);

        return voucherProviderMapper.toVoucherProviderResponseList(voucherProviderPage);
    }

    public Page<VoucherProviderResponse> getPopular(Pageable pageable) {
        Page<VoucherProvider> voucherProviderPage = voucherProviderRepository.findByIsPopularTrue(pageable);

        return voucherProviderMapper.toVoucherProviderResponseList(voucherProviderPage);
    }

    public Page<VoucherProviderResponse> getByCategory(String category, Pageable pageable) {
        VoucherProviderCategory voucherProviderCategory = VoucherProviderCategory.from(category);

        Page<VoucherProvider> voucherProviderPage = voucherProviderRepository.findByCategory(voucherProviderCategory, pageable);

        return voucherProviderMapper.toVoucherProviderResponseList(voucherProviderPage);
    }

    public VoucherProviderDetailResponse getDetail(Long id) {
        Optional<VoucherProvider> optionalVoucherProvider = voucherProviderRepository.findById(id);
        if(optionalVoucherProvider.isEmpty()) {
            throw new InvalidVoucherProviderIdException(ErrorCode.BAD_REQUEST, "해당 VoucherProvider ID는 존재하지 않습니다.");
        }
        VoucherProvider voucherProvider = optionalVoucherProvider.get();

        return voucherProviderDetailMapper.toVoucherProviderDetailResponse(voucherProvider);
    }

    public void update(Long id, VoucherProviderUpdateRequest updateRequest) {
        Optional<VoucherProvider> optionalVoucherProvider = voucherProviderRepository.findById(id);
        if(optionalVoucherProvider.isEmpty()) {
            throw new InvalidVoucherProviderIdException(ErrorCode.BAD_REQUEST, "해당 VoucherProvider ID는 존재하지 않습니다.");
        }
        VoucherProvider voucherProvider = optionalVoucherProvider.get();

        voucherProvider.update(
                updateRequest.getName(),
                updateRequest.getIsPopular(),
                updateRequest.getHomePage(),
                updateRequest.getCsCenter(),
                updateRequest.getCardDiscount(),
                updateRequest.getPhoneDiscount(),
                updateRequest.getDescription(),
                updateRequest.getPublisher(),
                updateRequest.getLogoImageUrl(),
                updateRequest.getNote()
        );

        try {
            voucherProviderRepository.save(voucherProvider);
        } catch(Exception e) {
            throw new DuplicateVoucherProviderNameException(ErrorCode.BAD_REQUEST, "이미 존재하는 상품권 제조사명 입니다.");
        }
    }

    public void delete(Long id) {
        Optional<VoucherProvider> optionalVoucherProvider = voucherProviderRepository.findById(id);
        if(optionalVoucherProvider.isEmpty()) {
            throw new InvalidVoucherProviderIdException(ErrorCode.BAD_REQUEST, "해당 VoucherProvider ID는 존재하지 않습니다.");
        }
        VoucherProvider voucherProvider = optionalVoucherProvider.get();

        voucherProviderRepository.delete(voucherProvider);
    }
}
