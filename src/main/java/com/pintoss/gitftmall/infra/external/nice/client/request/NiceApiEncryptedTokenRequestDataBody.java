package com.pintoss.gitftmall.infra.external.nice.client.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NiceApiEncryptedTokenRequestDataBody {
    public String req_dtim;
    public String req_no;
    public String enc_mode;
}
