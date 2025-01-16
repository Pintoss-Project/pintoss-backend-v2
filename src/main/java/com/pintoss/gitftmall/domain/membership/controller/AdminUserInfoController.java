package com.pintoss.gitftmall.domain.membership.controller;

import com.pintoss.gitftmall.common.dto.ApiResponse;
import com.pintoss.gitftmall.domain.membership.application.AdminUserInfoService;
import com.pintoss.gitftmall.domain.membership.application.result.UserInfoResult;
import com.pintoss.gitftmall.domain.membership.model.User;
import com.pintoss.gitftmall.domain.membership.model.value.RoleEnum;
import com.pintoss.gitftmall.infra.persistence.membership.UserRepositoryImpl;
import com.pintoss.gitftmall.infra.security.interceptor.AuthorizationRequired;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminUserInfoController {

    private final AdminUserInfoService adminUserInfoService;

    @GetMapping("/user/all")
    @AuthorizationRequired(RoleEnum.ADMIN)
    public ApiResponse<List<UserInfoResult>> findAllUserInfo(@PageableDefault(page = 0, size = 10) Pageable pageable) {
        List<UserInfoResult> userInfoResults = adminUserInfoService.findUserInfo(pageable);

        return ApiResponse.of(HttpStatus.OK, "전체 회원을 조회했습니다.", userInfoResults);
    }
}
