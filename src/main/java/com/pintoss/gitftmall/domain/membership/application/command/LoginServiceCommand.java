package com.pintoss.gitftmall.domain.membership.application.command;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginServiceCommand {

    private String email;
    private String password;

    public LoginServiceCommand(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
