package com.pintoss.gitftmall.domain.order.domain.vo;

import jakarta.persistence.Embeddable;
import jakarta.persistence.criteria.Order;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@EqualsAndHashCode
public class OrderNo {

    private static final SecureRandom RANDOM = new SecureRandom();
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyyMMdd");

    private final String value;

    private OrderNo(String value) {
        this.value = value;
    }

    protected OrderNo() {
        this.value = generateOrderNo();
    }

    public static OrderNo create() {
        return new OrderNo(generateOrderNo());
    }

    private static String generateOrderNo(){
        String datePart = LocalDate.now().format(DATE_FORMAT); // "YYYYMMdd"
        int randomPart = 10000000 + RANDOM.nextInt(90000000); // 8자리 랜덤 숫자
        return datePart + "-" + randomPart;
    }

    @Override
    public String toString() {
        return value;
    }

}
