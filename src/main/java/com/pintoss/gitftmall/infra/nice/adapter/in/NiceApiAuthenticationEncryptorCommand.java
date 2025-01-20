package com.pintoss.gitftmall.infra.nice.adapter.in;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NiceApiAuthenticationEncryptorCommand {

    private String key;
    private String iv;
    private String encData;
}
