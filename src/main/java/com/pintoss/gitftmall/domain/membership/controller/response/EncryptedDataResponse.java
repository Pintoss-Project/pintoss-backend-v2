package com.pintoss.gitftmall.domain.membership.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EncryptedDataResponse {
    private String token_version_id;
    private String enc_data;
    private String integrity_value;
}
