package com.pintoss.gitftmall.domain.membership.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NiceVerifyResponse {
    private boolean isSuccess;

    private String tel;
}
