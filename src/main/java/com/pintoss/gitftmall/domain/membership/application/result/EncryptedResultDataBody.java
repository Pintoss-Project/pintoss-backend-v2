package com.pintoss.gitftmall.domain.membership.application.result;

import lombok.Data;

@Data
public class EncryptedResultDataBody {
    private String rsp_cd;
    private String res_msg;
    private String result_cd;
    private String site_code;
    private String token_version_id;
    private String token_val;
    private String period;
}
