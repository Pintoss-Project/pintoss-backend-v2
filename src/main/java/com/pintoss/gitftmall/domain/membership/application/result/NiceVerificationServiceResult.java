package com.pintoss.gitftmall.domain.membership.application.result;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NiceVerificationServiceResult {

    private Boolean isSuccess;
    private String tel;
}
