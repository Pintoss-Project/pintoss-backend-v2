package com.pintoss.gitftmall.domain.product.model.value;

import com.pintoss.gitftmall.common.exceptions.ErrorCode;
import com.pintoss.gitftmall.common.exceptions.client.EmptyURLException;
import com.pintoss.gitftmall.common.exceptions.client.InvalidURLException;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class HomePage {
    private String url;

    public HomePage(String url) {
        validate(url);
        this.url = url;
    }

    private void validate(String url) {
        // TODO : 유효성 검증
        // Case1 . url
        // Case2 . 빈값, null
        // 이외에 IllegalArgumentException을 던진다.
        if (url == null || url.isBlank()) {
            throw new EmptyURLException(ErrorCode.MISSING_REQUEST_FIELD, "홈페이지 URL은 필수 입력 값입니다.");
        }
        if (!url.matches("^(http|https)://.*")) {
            throw new InvalidURLException(ErrorCode.INVALID_REQUEST_FIELD, "유효한 URL 형식이 아닙니다. (http:// 또는 https://로 시작해야 합니다.)");
        }
    }
}