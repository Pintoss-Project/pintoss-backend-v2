package com.pintoss.gitftmall.domain.membership.application.command;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReissueServiceCommand {

    private String accessToken;
    private String refreshToken;

}
