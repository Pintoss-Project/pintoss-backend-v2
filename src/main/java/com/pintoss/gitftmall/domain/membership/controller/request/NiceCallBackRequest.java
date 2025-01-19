package com.pintoss.gitftmall.domain.membership.controller.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NiceCallBackRequest {
    private String tokenVersionId;
    private String encData;
    private String integrityValue;
    private String token_version_id;
    private String enc_data;
    private String integrity_value;
}
