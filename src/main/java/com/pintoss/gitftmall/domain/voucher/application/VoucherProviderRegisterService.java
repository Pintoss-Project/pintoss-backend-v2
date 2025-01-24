package com.pintoss.gitftmall.domain.voucher.application;

import com.pintoss.gitftmall.common.exceptions.ErrorCode;
import com.pintoss.gitftmall.common.exceptions.voucher.DuplicateVoucherProviderNameException;
import com.pintoss.gitftmall.domain.voucher.application.command.VoucherProviderRegisterServiceCommand;
import com.pintoss.gitftmall.domain.voucher.model.VoucherProvider;
import com.pintoss.gitftmall.domain.voucher.model.value.ContactInfo;
import com.pintoss.gitftmall.domain.voucher.model.value.CsCenter;
import com.pintoss.gitftmall.domain.voucher.model.value.Discount;
import com.pintoss.gitftmall.domain.voucher.model.value.HomePage;
import com.pintoss.gitftmall.domain.voucher.repository.IVoucherProviderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VoucherProviderRegisterService {

    private final IVoucherProviderRepository voucherProviderRepository;

    public void register(VoucherProviderRegisterServiceCommand command){

        if( voucherProviderRepository.existsByName(command.getName()) ) {
            throw new DuplicateVoucherProviderNameException(ErrorCode.BAD_REQUEST, "이미 존재하는 상품권 제조사입니다.");
        }

        VoucherProvider voucherProvider = VoucherProvider.create(
                command.getName(),
                command.isPopular(),
                new ContactInfo(
                        new HomePage(command.getHomePage()),
                        new CsCenter(command.getCsCenter())
                ),
                new Discount(
                        command.getCardDiscount(),
                        command.getPhoneDiscount()
                ),
                command.getDescription(),
                command.getPublisher(),
                command.getCategory(),
                command.getLogoImageUrl(),
                command.getNote(),
                command.getIndex()
        );

        voucherProviderRepository.save(voucherProvider);
    }
}
