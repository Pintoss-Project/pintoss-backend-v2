package com.pintoss.gitftmall.domain.membership.application.command;

import lombok.Data;

@Data
public class AccessTokenResult {
    private AccessTokenResultDataHeader dataHeader;
    private AccessTokenResultDataBody dataBody;
}
