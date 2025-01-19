package com.pintoss.gitftmall.domain.membership.application.result;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EncryptedDataResult {
    private String token_version_id;
    private String enc_data;
    private String integrity_value;
}
