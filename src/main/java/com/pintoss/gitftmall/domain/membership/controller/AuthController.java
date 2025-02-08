package com.pintoss.gitftmall.domain.membership.controller;

import com.pintoss.gitftmall.common.dto.ApiResponse;
import com.pintoss.gitftmall.common.exceptions.ErrorCode;
import com.pintoss.gitftmall.common.exceptions.client.MissingTokenException;
import com.pintoss.gitftmall.common.utils.HttpServletUtils;
import com.pintoss.gitftmall.domain.membership.application.LoginService;
import com.pintoss.gitftmall.domain.membership.application.RegisterService;
import com.pintoss.gitftmall.domain.membership.application.ReissueService;
import com.pintoss.gitftmall.domain.membership.application.command.LoginServiceCommand;
import com.pintoss.gitftmall.domain.membership.application.command.RegisterServiceCommand;
import com.pintoss.gitftmall.domain.membership.application.command.ReissueServiceCommand;
import com.pintoss.gitftmall.domain.membership.application.result.LoginResult;
import com.pintoss.gitftmall.domain.membership.application.result.ReissueResult;
import com.pintoss.gitftmall.domain.membership.controller.request.LoginRequest;
import com.pintoss.gitftmall.domain.membership.controller.request.RegisterRequest;
import com.pintoss.gitftmall.domain.membership.controller.response.LoginResponse;
import com.pintoss.gitftmall.domain.membership.controller.response.ReissueResponse;
import com.pintoss.gitftmall.domain.membership.model.value.RoleEnum;
import com.pintoss.gitftmall.infra.config.web.interceptor.AuthorizationRequired;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class AuthController {

    private final RegisterService registerService;
    private final LoginService loginService;
    private final ReissueService reissueService;
    private final HttpServletUtils servletUtils;

    @PostMapping("/register")
    public ApiResponse<Void> register(@RequestBody @Valid RegisterRequest request) {
        RegisterServiceCommand command = new RegisterServiceCommand(request.getEmail(), request.getPassword(), request.getName(), request.getPhone());

        registerService.signup(command);

        return ApiResponse.ok(null);
    }

    @PostMapping("/login")
    public ApiResponse<LoginResponse> login(@RequestBody @Valid LoginRequest request, HttpServletResponse servletResponse) {
        LoginServiceCommand command = new LoginServiceCommand(request.getEmail(), request.getPassword());

        LoginResult loginResult = loginService.login(command);

        // TODO : 만료 기간은 미정 임의 값 입니다.
//        servletUtils.addCookie(servletResponse, "AccessToken", loginResult.getAccessToken(), (int) 1000000000L);
        servletUtils.addCookie(servletResponse, "RefreshToken", loginResult.getRefreshToken(), (int) 1000000000L);

        return ApiResponse.ok(new LoginResponse(loginResult.getAccessToken()));
    }

    @PostMapping("/logout")
    @AuthorizationRequired(value = {RoleEnum.USER, RoleEnum.ADMIN})
    public ApiResponse<Void> logout(HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        servletUtils.removeCookie(servletRequest, servletResponse, "AccessToken");

        return ApiResponse.ok(null);
    }

    @PostMapping("/reissue")
    public ApiResponse<ReissueResponse> reissue(HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        String accessToken = servletUtils.getAccessToken(servletRequest)
                .orElseThrow(() -> new MissingTokenException(ErrorCode.INVALID_ACCESS, "액세스 토큰이 빈 값입니다."));

        String refreshToken = servletUtils.getCookie(servletRequest, "RefreshToken")
                .map(Cookie::getValue)
                .orElseThrow(() -> new MissingTokenException(ErrorCode.INVALID_ACCESS, "리프레쉬 토큰이 빈 값입니다."));

        ReissueServiceCommand command = new ReissueServiceCommand(accessToken, refreshToken);

        ReissueResult reissue = reissueService.reissue(command);

        // TODO : 만료 기간은 미정 임의 값 입니다.
//        servletUtils.addCookie(servletResponse, "AccessToken", reissue.getAccessToken(), (int) 10000000L);
        servletUtils.addCookie(servletResponse, "RefreshToken", reissue.getRefreshToken(), (int) 10000000L);

        return ApiResponse.ok(new ReissueResponse(reissue.getAccessToken()));
    }

    @GetMapping("/check-id")
    public ApiResponse<Boolean> checkEmailDuplicate(@RequestParam(name = "email") String email) {
        Boolean rs = registerService.checkEmailDuplicate(email);
        return ApiResponse.ok(rs);
    }

    @GetMapping("/check-phone")
    public ApiResponse<Boolean> checkPhoneDuplicate(@RequestParam(name = "phone") String phone) {
        Boolean rs = registerService.checkPhoneDuplicate(phone);
        return ApiResponse.ok(rs);
    }
}
