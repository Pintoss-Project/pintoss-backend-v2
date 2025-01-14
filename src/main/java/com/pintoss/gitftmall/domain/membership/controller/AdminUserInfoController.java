package com.pintoss.gitftmall.domain.membership.controller;

import com.pintoss.gitftmall.common.dto.ApiResponse;
import com.pintoss.gitftmall.domain.membership.application.result.UserInfoResult;
import com.pintoss.gitftmall.domain.membership.model.User;
import com.pintoss.gitftmall.domain.membership.model.value.RoleEnum;
import com.pintoss.gitftmall.infra.persistence.membership.UserRepositoryImpl;
import com.pintoss.gitftmall.infra.security.interceptor.AuthorizationRequired;
import lombok.RequiredArgsConstructor;
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

    private final UserRepositoryImpl userRepository;

    @GetMapping("/user/all")
    @AuthorizationRequired(RoleEnum.ADMIN)
    public ApiResponse<List<UserInfoResult>> findAllUserInfo() {
        List<User> userList = userRepository.findAllUsers();

        List<UserInfoResult> userInfoResults = userList.stream()
                .map(user -> new UserInfoResult(
                        user.getEmail().toString(),
                        user.getName(),
                        user.getPhone().toString()
                ))
                .toList();

        return ApiResponse.of(HttpStatus.OK, "전체 회원을 조회했습니다.", userInfoResults);
    }
}
