package com.pintoss.gitftmall.domain.membership.application.result;

import lombok.Data;

@Data
public class EncryptedResult {
    public EncryptedResultDataHeader dataHeader;
    public EncryptedResultDataBody dataBody;

}
