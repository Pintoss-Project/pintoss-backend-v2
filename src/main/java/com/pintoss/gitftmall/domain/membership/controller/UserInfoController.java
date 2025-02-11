package com.pintoss.gitftmall.domain.membership.controller;

import com.pintoss.gitftmall.core.dto.ApiResponse;
import com.pintoss.gitftmall.core.exceptions.client.NotFoundMemberException;
import com.pintoss.gitftmall.core.util.HttpServletUtils;
import com.pintoss.gitftmall.domain.membership.controller.request.UserInfoResponse;
import com.pintoss.gitftmall.domain.membership.infra.UserRepositoryImpl;
import com.pintoss.gitftmall.domain.membership.model.User;
import com.pintoss.gitftmall.domain.membership.application.TokenManageService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserInfoController {

    private final HttpServletUtils servletUtils;
    private final TokenManageService tokenManageService;
    private final UserRepositoryImpl userRepository;

    @GetMapping("/user_info")
    public ApiResponse<UserInfoResponse> userInfo(HttpServletRequest servletRequest, HttpServletResponse servletResponse, Authentication authentication) {
        String accessToken = servletUtils.getCookie(servletRequest, "AccessToken")
                .map(Cookie::getValue)
                .orElseThrow(null);

        String subject = tokenManageService.getSubject(accessToken);

        User user = userRepository.findById(Integer.parseInt(subject)).orElseThrow(
                () -> new NotFoundMemberException("존재하지 않는 회원입니다.")
        );

        UserInfoResponse userInfoResult = new UserInfoResponse(user.getEmail().toString(), user.getName(), user.getPhone().toString());

        return ApiResponse.of(HttpStatus.OK, "회원정보를 조회했습니다.", userInfoResult);
    }
}
