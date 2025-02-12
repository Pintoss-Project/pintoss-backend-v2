package com.pintoss.gitftmall.domain.membership.controller;

import com.pintoss.gitftmall.core.dto.ApiResponse;
import com.pintoss.gitftmall.domain.membership.application.AdminUserInfoService;
import com.pintoss.gitftmall.domain.membership.controller.request.UserInfoResponse;
import com.pintoss.gitftmall.domain.membership.domain.vo.RoleEnum;
import com.pintoss.gitftmall.core.web.interceptor.AuthorizationRequired;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminUserInfoController {

    private final AdminUserInfoService adminUserInfoService;

    @GetMapping("/user/all")
    @AuthorizationRequired(RoleEnum.ADMIN)
    public ApiResponse<List<UserInfoResponse>> findAllUserInfo(@PageableDefault(page = 0, size = 10) Pageable pageable) {
        List<UserInfoResponse> userInfoResults = adminUserInfoService.findUserInfo(pageable);

        return ApiResponse.of(HttpStatus.OK, "전체 회원을 조회했습니다.", userInfoResults);
    }
}
