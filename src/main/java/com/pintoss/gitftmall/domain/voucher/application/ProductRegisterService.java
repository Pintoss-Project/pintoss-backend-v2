package com.pintoss.gitftmall.domain.voucher.application;

import com.pintoss.gitftmall.common.exceptions.ErrorCode;
import com.pintoss.gitftmall.common.exceptions.client.DuplicateProductNameException;
import com.pintoss.gitftmall.domain.voucher.application.command.ProductRegisterServiceCommand;
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
public class ProductRegisterService {

    private final IVoucherProviderRepository productRepository;

    public void register(ProductRegisterServiceCommand command){

        if( productRepository.existsByName(command.getName()) ) {
            throw new DuplicateProductNameException(ErrorCode.BAD_REQUEST, "이미 존재하는 상품입니다.");
        }

        VoucherProvider product = VoucherProvider.create(
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

        productRepository.save(product);
    }

}
