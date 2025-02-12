package com.pintoss.gitftmall.domain.membership.controller.response;

import com.pintoss.gitftmall.domain.membership.application.result.NiceVerificationResult;
import lombok.Data;

@Data
public class VerifyResponse {
    private boolean isSuccess;

    private String name;

    private String tel;

    private VerifyResponse(boolean isSuccess, String name, String tel) {
        this.isSuccess = isSuccess;
        this.name = name;
        this.tel = tel;
    }

    // 팩토리 메서드를 활용하여 변환 책임을 한 곳에서 관리
    public static VerifyResponse from(NiceVerificationResult result) {
        return new VerifyResponse(result.getIsSuccess(), result.getName(), result.getTel());
    }
}
