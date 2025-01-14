package com.pintoss.gitftmall.domain.membership.controller.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NiceAuthResultRequest {
    private String resultcode;
    private String requestno;
    private String enctime;
    private String sitecode;
    private String responseno;
    private String authtype;
}
