package com.pintoss.gitftmall.domain.membership.domain.vo;

import com.pintoss.gitftmall.core.exceptions.client.EmptyPhoneException;
import com.pintoss.gitftmall.core.exceptions.client.InvalidPhoneFormatException;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.regex.Pattern;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Phone {

    private static final String PHONE_REGEX = "^010-\\d{4}-\\d{4}$";;
    private static final Pattern PHONE_PATTERN = Pattern.compile(PHONE_REGEX);

    private String phone;

    public Phone(String phone) {
        validate(phone);
        this.phone = phone;
    }

    private void validate(String value) {
        if(value == null || value.isBlank()) {
            throw new EmptyPhoneException("전화번호는 필수 입력 값 입니다..");
        }
        if(!PHONE_PATTERN.matcher(value).matches()) {
            throw new InvalidPhoneFormatException("유효하지 않은 전화번호 형식입니다. (010-xxxx-xxxx)");
        }
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Phone target = (Phone) o;
        return Objects.equals(phone, target.phone);
    }

    @Override
    public int hashCode() {
        return  Objects.hash(phone);
    }

    @Override
    public String toString() {
        return phone;
    }
}
