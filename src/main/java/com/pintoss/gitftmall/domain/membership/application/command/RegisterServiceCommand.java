package com.pintoss.gitftmall.domain.membership.application.command;

import lombok.Data;

@Data
public class RegisterServiceCommand {

    private String email;
    private String password;
    private String name;
    private String phone;

    public RegisterServiceCommand(String email, String password, String name, String phone) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone = phone;
    }

}
