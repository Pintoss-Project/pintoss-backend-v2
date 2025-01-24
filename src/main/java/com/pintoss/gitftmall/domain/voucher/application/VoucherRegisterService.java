package com.pintoss.gitftmall.domain.voucher.application;

import com.pintoss.gitftmall.common.exceptions.ErrorCode;
import com.pintoss.gitftmall.common.exceptions.voucher.InvalidVoucherProviderIdException;
import com.pintoss.gitftmall.domain.voucher.application.command.VoucherRegisterServiceCommand;
import com.pintoss.gitftmall.domain.voucher.model.Voucher;
import com.pintoss.gitftmall.domain.voucher.model.VoucherProvider;
import com.pintoss.gitftmall.domain.voucher.repository.IVoucherProviderRepository;
import com.pintoss.gitftmall.domain.voucher.repository.IVoucherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VoucherRegisterService {

    private final IVoucherRepository voucherRepository;
    private final IVoucherProviderRepository voucherProviderRepository;

    public void register(Long voucherProviderId, VoucherRegisterServiceCommand command) {
//        VoucherProvider 가져오기
        Optional<VoucherProvider> optionalVoucherProvider = voucherProviderRepository.findById(voucherProviderId);

        if(optionalVoucherProvider.isEmpty()) {
            throw new InvalidVoucherProviderIdException(ErrorCode.BAD_REQUEST, "해당 VoucherProvider ID는 존재하지 않습니다.");
        }
        VoucherProvider voucherProvider = optionalVoucherProvider.get();

//        Voucher 생성
        Voucher voucher = Voucher.create(
                command.getName(),
                command.getPrice(),
                command.getStock()
        );

//        Voucher에 VoucherProvider 연결
        voucherProvider.addVoucher(voucher);

        voucherRepository.save(voucher);
    }
}
