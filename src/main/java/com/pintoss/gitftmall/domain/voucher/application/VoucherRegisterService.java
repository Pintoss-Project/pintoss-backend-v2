package com.pintoss.gitftmall.domain.voucher.application;

import com.pintoss.gitftmall.domain.voucher.application.command.VoucherRegisterServiceCommand;
import com.pintoss.gitftmall.domain.voucher.domain.Voucher;
import com.pintoss.gitftmall.domain.voucher.domain.repository.IVoucherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VoucherRegisterService {

    private final IVoucherRepository voucherRepository;

    public void register(VoucherRegisterServiceCommand command) {
        Voucher voucher = Voucher.create(
                command.getVoucherProviderId(),
                command.getPrice(),
                command.getStock()
        );

        voucherRepository.save(voucher);
    }

}
