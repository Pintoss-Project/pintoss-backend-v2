package com.pintoss.gitftmall.domain.membership.model.value;


import com.pintoss.gitftmall.common.exceptions.client.EmptyEmailException;
import com.pintoss.gitftmall.common.exceptions.client.InvalidEmailFormatException;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.regex.Pattern;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Email {

    private static final String EMAIL_REGEX = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    private String email;

    public Email(String email) {
        validate(email);
        this.email = email;
    }

    /**
     * 이메일 유효성 검증
     *
     * @param value 검증할 이메일 값
     */
    private void validate(String value) {
        if (value == null || value.isBlank()) {
            throw new EmptyEmailException("이메일은 비어있을 수 없습니다.");
        }
        if (!EMAIL_PATTERN.matcher(value).matches()) {
            throw new InvalidEmailFormatException("유효하지 않은 이메일 형식입니다.");
        }
    }

    /**
     * 두 이메일 객체의 동등성 비교
     *
     * @param o 비교할 객체
     * @return 동등 여부
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email target = (Email) o;
        return Objects.equals(email, target.email);
    }

    /**
     * 해시 코드 생성
     *
     * @return 해시 코드 값
     */
    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    /**
     * 문자열 반환
     *
     * @return 이메일 문자열
     */
    @Override
    public String toString() {
        return email;
    }
}
