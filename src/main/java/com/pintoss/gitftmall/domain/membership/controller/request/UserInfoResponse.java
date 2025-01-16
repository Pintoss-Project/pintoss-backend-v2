package com.pintoss.gitftmall.domain.membership.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserInfoResponse {

    private String email;
    private String name;
    private String phone;

}
