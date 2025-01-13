package com.pintoss.gitftmall.domain.membership.application.command;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EncryptedRequest {
    public EncryptedRequestDataHeader dataHeader;
    public EncryptedRequestDataBody dataBody;
}
