package com.pintoss.gitftmall.domain.voucher.application.command;

import com.pintoss.gitftmall.core.exceptions.ErrorCode;
import com.pintoss.gitftmall.core.exceptions.client.DuplicateProductNameException;
import com.pintoss.gitftmall.domain.voucher.application.dto.VoucherProviderRegisterServiceCommand;
import com.pintoss.gitftmall.domain.voucher.domain.Voucher;
import com.pintoss.gitftmall.domain.voucher.domain.repository.VoucherRepository;
import com.pintoss.gitftmall.domain.voucher.domain.vo.Discount;
import com.pintoss.gitftmall.domain.voucher.domain.VoucherProvider;
import com.pintoss.gitftmall.domain.voucher.domain.vo.ContactInfo;
import com.pintoss.gitftmall.domain.voucher.domain.vo.CsCenter;
import com.pintoss.gitftmall.domain.voucher.domain.vo.HomePage;
import com.pintoss.gitftmall.domain.voucher.domain.repository.VoucherProviderRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VoucherProviderRegisterService {

    private final VoucherProviderRepository voucherProviderRepository;
    private final VoucherRepository voucherRepository;

    public void register(VoucherProviderRegisterServiceCommand command){

        if( voucherProviderRepository.existsByName(command.getName()) ) {
            throw new DuplicateProductNameException(ErrorCode.BAD_REQUEST, "이미 존재하는 상품입니다.");
        }

        VoucherProvider voucherProvider = VoucherProvider.create(
                command.getName(),
                command.getCode(),
                new Discount(command.getCardDiscount(), command.getPhoneDiscount()),
                new ContactInfo(
                        new HomePage(command.getHomePage()),
                        new CsCenter(command.getCsCenter())
                ),
                command.getDescription(),
                command.getPublisher(),
                command.getNote(),
                command.getImageUrl()
        );

        voucherProviderRepository.save(voucherProvider);

        List<Voucher> vouchers = command.getVouchers().stream().map(v -> {
            return Voucher.create(voucherProvider.getId(), v.getName(), v.getPrice(), v.getStock());
        }).collect(Collectors.toList());

        voucherRepository.saveAll(vouchers);

    }

}
