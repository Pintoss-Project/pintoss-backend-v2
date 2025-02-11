package com.pintoss.gitftmall.domain.membership.infra.nice.client.request;

import lombok.Data;

@Data
public class NiceApiAccessTokenResult {
    private NiceApiAccessTokenResultDataHeader dataHeader;
    private NiceApiAccessTokenResultDataBody dataBody;
}
