package com.pintoss.gitftmall.domain.membership.application.result;

import com.pintoss.gitftmall.domain.membership.model.value.Email;
import com.pintoss.gitftmall.domain.membership.model.value.Phone;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserInfoResult {

    private String email;
    private String name;
    private String phone;

}
