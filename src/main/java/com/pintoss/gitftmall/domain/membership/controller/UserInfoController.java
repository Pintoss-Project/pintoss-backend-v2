package com.pintoss.gitftmall.domain.membership.controller;

import com.pintoss.gitftmall.core.dto.ApiResponse;
import com.pintoss.gitftmall.core.util.SecurityContextUtils;
import com.pintoss.gitftmall.core.web.interceptor.AuthorizationRequired;
import com.pintoss.gitftmall.domain.membership.application.UserQueryService;
import com.pintoss.gitftmall.domain.membership.controller.request.UserInfoResponse;
import com.pintoss.gitftmall.domain.membership.domain.vo.RoleEnum;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserInfoController {

    private final UserQueryService userQueryService;

    @GetMapping("/info")
    @AuthorizationRequired({ RoleEnum.ADMIN, RoleEnum.USER })
    public ApiResponse<UserInfoResponse> userInfo() {
        Long userId = SecurityContextUtils.getUserId();

        UserInfoResponse userInfo = userQueryService.getUserInfo(userId);

        return ApiResponse.ok(userInfo);
    }
}
