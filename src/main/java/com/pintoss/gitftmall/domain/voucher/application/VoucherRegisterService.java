package com.pintoss.gitftmall.domain.voucher.application;

import com.pintoss.gitftmall.domain.voucher.application.command.VoucherRegisterServiceCommand;
import com.pintoss.gitftmall.domain.voucher.model.Voucher;
import com.pintoss.gitftmall.domain.voucher.model.value.Discount;
import com.pintoss.gitftmall.domain.voucher.repository.IVoucherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VoucherRegisterService {

    private final IVoucherRepository voucherRepository;

    public void register(VoucherRegisterServiceCommand command) {
        Discount discount = new Discount(command.getCardDiscount(), command.getPhoneDiscount());
        Voucher voucher = Voucher.create(
                command.getVoucherProviderId(),
                discount,
                command.getPrice(),
                command.getStock()
        );

        voucherRepository.save(voucher);
    }

}
