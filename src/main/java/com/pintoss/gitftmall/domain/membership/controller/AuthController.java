package com.pintoss.gitftmall.domain.membership.controller;

import com.pintoss.gitftmall.common.dto.ApiResponse;
import com.pintoss.gitftmall.common.utils.HttpServletUtils;
import com.pintoss.gitftmall.domain.membership.application.LoginService;
import com.pintoss.gitftmall.domain.membership.application.RegisterService;
import com.pintoss.gitftmall.domain.membership.application.result.LoginResult;
import com.pintoss.gitftmall.domain.membership.application.command.LoginServiceCommand;
import com.pintoss.gitftmall.domain.membership.application.command.RegisterServiceCommand;
import com.pintoss.gitftmall.domain.membership.controller.request.LoginRequest;
import com.pintoss.gitftmall.domain.membership.controller.request.RegisterRequest;
import com.pintoss.gitftmall.domain.membership.model.User;
import com.pintoss.gitftmall.infra.persistence.membership.UserJpaRepository;
import com.pintoss.gitftmall.infra.persistence.membership.UserRepositoryImpl;
import jakarta.servlet.http.HttpServletResponse;
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
    private final LoginService loginService;
    private final HttpServletUtils servletUtils;

    @PostMapping("/register")
    public ApiResponse<Void> register(@RequestBody @Valid RegisterRequest request) {
        RegisterServiceCommand command = new RegisterServiceCommand(request.getEmail(), request.getPassword(), request.getName(), request.getPhone());

        registerService.signup(command);

        return ApiResponse.ok(null);
    }

    @PostMapping("/login")
    public ApiResponse<LoginResult> login(@RequestBody @Valid LoginRequest request, HttpServletResponse servletResponse) {
        LoginServiceCommand command = new LoginServiceCommand(request.getEmail(), request.getPassword());

        // TODO loginService에서 login하는 과정에서 에러 발생. command에 email과 pw는 잘 넘어옴.
        LoginResult loginResult = loginService.login(command);

        // TODO : 만료 기간은 미정 임의 값 입니다.
        servletUtils.addCookie(servletResponse, "AccessToken", loginResult.getAccessToken(), (int) 1000000000L);
        servletUtils.addCookie(servletResponse, "RefreshToken", loginResult.getRefreshToken(), (int) 1000000000L);

        return ApiResponse.ok(loginResult);
    }

}
