package com.pintoss.gitftmall.domain.membership.controller;

import com.pintoss.gitftmall.common.dto.ApiResponse;
import com.pintoss.gitftmall.common.exceptions.ErrorCode;
import com.pintoss.gitftmall.common.exceptions.client.MissingTokenException;
import com.pintoss.gitftmall.common.exceptions.client.NotFoundMemberException;
import com.pintoss.gitftmall.common.utils.HttpServletUtils;
import com.pintoss.gitftmall.domain.membership.application.result.UserInfoResult;
import com.pintoss.gitftmall.domain.membership.model.User;
import com.pintoss.gitftmall.domain.membership.service.TokenManageService;
import com.pintoss.gitftmall.infra.persistence.membership.UserRepositoryImpl;
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
    public ApiResponse<?> userInfo(HttpServletRequest servletRequest, HttpServletResponse servletResponse, Authentication authentication) {
        String accessToken = servletUtils.getCookie(servletRequest, "AccessToken")
                .map(Cookie::getValue)
                .orElseThrow(() -> new MissingTokenException(ErrorCode.INVALID_ACCESS, "액세스 토큰이 빈 값입니다."));

        String subject = tokenManageService.getSubject(accessToken);

        User user = userRepository.findById(Integer.parseInt(subject)).orElseThrow(
                () -> new NotFoundMemberException("존재하지 않는 회원입니다.")
        );

        UserInfoResult userInfoResult = new UserInfoResult(user.getEmail().toString(), user.getName(), user.getPhone().toString());

        return ApiResponse.of(HttpStatus.OK, "회원정보를 조회했습니다.", userInfoResult);
    }
}
