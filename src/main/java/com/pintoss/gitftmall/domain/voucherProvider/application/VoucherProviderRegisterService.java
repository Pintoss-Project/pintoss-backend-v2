package com.pintoss.gitftmall.domain.voucherProvider.application;

import com.pintoss.gitftmall.core.exceptions.ErrorCode;
import com.pintoss.gitftmall.core.exceptions.client.DuplicateProductNameException;
import com.pintoss.gitftmall.domain.voucherProvider.domain.vo.Discount;
import com.pintoss.gitftmall.domain.voucherProvider.application.command.VoucherProviderRegisterServiceCommand;
import com.pintoss.gitftmall.domain.voucherProvider.domain.VoucherProvider;
import com.pintoss.gitftmall.domain.voucherProvider.domain.vo.ContactInfo;
import com.pintoss.gitftmall.domain.voucherProvider.domain.vo.CsCenter;
import com.pintoss.gitftmall.domain.voucherProvider.domain.vo.HomePage;
import com.pintoss.gitftmall.domain.voucherProvider.domain.repository.VoucherProviderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VoucherProviderRegisterService {

    private final VoucherProviderRepository voucherProviderRepository;

    public void register(VoucherProviderRegisterServiceCommand command){

        if( voucherProviderRepository.existsByName(command.getName()) ) {
            throw new DuplicateProductNameException(ErrorCode.BAD_REQUEST, "이미 존재하는 상품입니다.");
        }

        VoucherProvider voucherProvider = VoucherProvider.create(
                command.getName(),
                command.getCode(),
                command.isPopular(),
                new Discount(command.getCardDiscount(), command.getPhoneDiscount()),
                new ContactInfo(
                        new HomePage(command.getHomePage()),
                        new CsCenter(command.getCsCenter())
                ),
                command.getDescription(),
                command.getPublisher(),
                command.getNote(),
                command.getIndex(),
                command.getImageUrl()
        );

        voucherProviderRepository.save(voucherProvider);
    }

}
