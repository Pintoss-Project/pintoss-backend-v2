package com.pintoss.gitftmall.infra.security.oauth.handler;

import com.pintoss.gitftmall.common.dto.ApiResponse;
import com.pintoss.gitftmall.common.utils.HttpServletUtils;
import com.pintoss.gitftmall.domain.membership.application.LoginService;
import com.pintoss.gitftmall.domain.membership.application.command.LoginServiceCommand;
import com.pintoss.gitftmall.domain.membership.application.result.LoginResult;
import com.pintoss.gitftmall.domain.membership.controller.request.LoginRequest;
import com.pintoss.gitftmall.infra.security.filter.jwt.TokenProvider;
import com.pintoss.gitftmall.infra.security.model.CustomDetails;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.token.Token;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class OAuthSuccessHandler implements AuthenticationSuccessHandler {

    //    private final RestTemplate restTemplate;
    private final HttpServletUtils servletUtils;
    private final TokenProvider tokenProvider;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();

        // Attributes에서 데이터 가져오기
        Map<String, Object> attributes = oAuth2User.getAttributes();

        // 여기서 분기 -> userId 있으면 로그인 성공, 아니면 provider와 email정보 담아서 주기
        Long userId = (Long) attributes.get("userId");
        String email = (String) attributes.get("email");
        String provider = (String) attributes.get("provider");

        // 기존 아이디 있는 경우
        if(userId != null) {
            String accessToken = tokenProvider.createAccessToken(String.valueOf(userId));
            String refreshToken = tokenProvider.createRefreshToken(String.valueOf(userId));

            servletUtils.addCookie(response, "AccessToken", accessToken, (int) 1000000000L);
            servletUtils.addCookie(response, "RefreshToken", refreshToken, (int) 1000000000L);

            response.sendRedirect("https://pin-toss.com");
        }
        // 신규 로그인
        else {
            servletUtils.addCookie(response, "email", email, (int) 1000000000L);
            servletUtils.addCookie(response, "provider", provider, (int) 1000000000L);

            response.sendRedirect("https://pin-toss.com/register?oauth=true");
        }
    }
}
