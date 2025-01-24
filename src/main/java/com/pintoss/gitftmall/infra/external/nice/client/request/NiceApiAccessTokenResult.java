package com.pintoss.gitftmall.infra.external.nice.client.request;

import lombok.Data;

@Data
public class NiceApiAccessTokenResult {
    private NiceApiAccessTokenResultDataHeader dataHeader;
    private NiceApiAccessTokenResultDataBody dataBody;
}
