package com.pintoss.gitftmall.domain.membership.application.command;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NiceVerificationServiceCommand {
    private String tokenVersionId;
    private String encData;
    private String integrityValue;
}
