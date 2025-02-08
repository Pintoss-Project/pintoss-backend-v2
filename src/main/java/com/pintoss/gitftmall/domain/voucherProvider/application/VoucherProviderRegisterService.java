package com.pintoss.gitftmall.domain.voucherProvider.application;

import com.pintoss.gitftmall.common.exceptions.ErrorCode;
import com.pintoss.gitftmall.common.exceptions.client.DuplicateProductNameException;
import com.pintoss.gitftmall.domain.voucherProvider.model.value.Discount;
import com.pintoss.gitftmall.domain.voucherProvider.application.command.VoucherProviderRegisterServiceCommand;
import com.pintoss.gitftmall.domain.voucherProvider.model.VoucherProvider;
import com.pintoss.gitftmall.domain.voucherProvider.model.value.ContactInfo;
import com.pintoss.gitftmall.domain.voucherProvider.model.value.CsCenter;
import com.pintoss.gitftmall.domain.voucherProvider.model.value.HomePage;
import com.pintoss.gitftmall.domain.voucherProvider.repository.IVoucherProviderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VoucherProviderRegisterService {

    private final IVoucherProviderRepository voucherProviderRepository;

    public void register(VoucherProviderRegisterServiceCommand command){

        if( voucherProviderRepository.existsByName(command.getName()) ) {
            throw new DuplicateProductNameException(ErrorCode.BAD_REQUEST, "이미 존재하는 상품입니다.");
        }

        VoucherProvider voucherProvider = VoucherProvider.create(
                command.getName(),
                command.isPopular(),
                new Discount(command.getCardDiscount(), command.getPhoneDiscount()),
                new ContactInfo(
                        new HomePage(command.getHomePage()),
                        new CsCenter(command.getCsCenter())
                ),
                command.getDescription(),
                command.getPublisher(),
                command.getNote(),
                command.getIndex()
        );

        voucherProviderRepository.save(voucherProvider);
    }

}
