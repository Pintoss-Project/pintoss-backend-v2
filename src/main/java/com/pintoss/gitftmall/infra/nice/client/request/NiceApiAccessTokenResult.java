package com.pintoss.gitftmall.infra.nice.client.request;

import lombok.Data;

@Data
public class NiceApiAccessTokenResult {
    private NiceApiAccessTokenResultDataHeader dataHeader;
    private NiceApiAccessTokenResultDataBody dataBody;
}
