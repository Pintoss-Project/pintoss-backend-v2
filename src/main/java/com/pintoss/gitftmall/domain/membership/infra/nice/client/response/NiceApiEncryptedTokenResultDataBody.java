package com.pintoss.gitftmall.domain.membership.infra.nice.client.response;

import lombok.Data;

@Data
public class NiceApiEncryptedTokenResultDataBody {
    private String rsp_cd;
    private String res_msg;
    private String result_cd;
    private String site_code;
    private String token_version_id;
    private String token_val;
    private long period;
}
