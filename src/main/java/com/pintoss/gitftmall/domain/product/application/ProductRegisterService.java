package com.pintoss.gitftmall.domain.product.application;

import com.pintoss.gitftmall.common.exceptions.ErrorCode;
import com.pintoss.gitftmall.common.exceptions.client.DuplicateProductNameException;
import com.pintoss.gitftmall.domain.product.application.command.ProductRegisterServiceCommand;
import com.pintoss.gitftmall.domain.product.model.Product;
import com.pintoss.gitftmall.domain.product.model.value.ContactInfo;
import com.pintoss.gitftmall.domain.product.model.value.CsCenter;
import com.pintoss.gitftmall.domain.product.model.value.Discount;
import com.pintoss.gitftmall.domain.product.model.value.HomePage;
import com.pintoss.gitftmall.domain.product.repository.IProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductRegisterService {

    private final IProductRepository productRepository;

    public void register(ProductRegisterServiceCommand command){

        if( productRepository.existsByName(command.getName()) ) {
            throw new DuplicateProductNameException(ErrorCode.BAD_REQUEST, "이미 존재하는 상품입니다.");
        }

        Product product = Product.create(
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
