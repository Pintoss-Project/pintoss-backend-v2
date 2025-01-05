package com.pintoss.gitftmall.domain.membership.controller;

import com.pintoss.gitftmall.common.dto.ApiResponse;
import com.pintoss.gitftmall.domain.membership.application.RegisterService;
import com.pintoss.gitftmall.domain.membership.application.command.LoginServiceCommand;
import com.pintoss.gitftmall.domain.membership.application.command.RegisterServiceCommand;
import com.pintoss.gitftmall.domain.membership.controller.request.LoginRequest;
import com.pintoss.gitftmall.domain.membership.controller.request.RegisterRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class AuthController {

    private final RegisterService registerService;

    @PostMapping("/register")
    public ApiResponse<Void> register(@RequestBody @Valid RegisterRequest request) {
        RegisterServiceCommand command = new RegisterServiceCommand(request.getEmail(), request.getPassword(), request.getName(), request.getPhone());

        registerService.signup(command);

        return ApiResponse.ok(null);
    }

}
