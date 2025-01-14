package com.pintoss.gitftmall.domain.membership.application.command;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EncryptedRequestDataBody {
    public String req_dtim;
    public String req_no;
    public String enc_mode;
}
