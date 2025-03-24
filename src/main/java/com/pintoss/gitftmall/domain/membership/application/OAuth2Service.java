package com.pintoss.gitftmall.domain.membership.application;

import com.pintoss.gitftmall.core.exceptions.ErrorCode;
import com.pintoss.gitftmall.core.exceptions.client.BadRequestException;
import com.pintoss.gitftmall.domain.membership.application.dto.OAuth2UserInfoResponse;
import com.pintoss.gitftmall.domain.membership.controller.response.OAuth2LoginSuccess;
import com.pintoss.gitftmall.domain.membership.controller.response.OAuth2Response;
import com.pintoss.gitftmall.domain.membership.controller.response.OAuth2SignupRequired;
import com.pintoss.gitftmall.domain.membership.domain.User;
import com.pintoss.gitftmall.domain.membership.domain.repository.UserRepository;
import com.pintoss.gitftmall.domain.membership.domain.service.TokenManageService;
import com.pintoss.gitftmall.domain.membership.domain.vo.LoginType;
import com.pintoss.gitftmall.domain.membership.domain.vo.OAuth2ProviderType;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OAuth2Service {

    private final ClientRegistrationRepository clientRegistrationRepository;
    private final OAuth2UserInfoStrategy oAuth2UserInfoStrategy;
    private final UserRepository userRepository;
    private final TokenManageService tokenManageService;

    @Transactional
    public OAuth2Response handleOAuthLogin(LoginType loginType, String code) {
        OAuth2UserInfoService userInfoService = oAuth2UserInfoStrategy.getOAuth2UserInfoService(loginType);
        OAuth2UserInfoResponse userInfo = userInfoService.getUserInfo(code);

        Optional<User> optionalUser = userRepository.findByEmail(userInfo.getEmail());

        if(optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.validateSameLoginType(loginType);
            String subject = user.getId().toString();
            String accessToken = tokenManageService.createToken(subject, false);
            String refreshToken = tokenManageService.createToken(subject, true);
            user.storeRefreshToken(refreshToken);
            return new OAuth2LoginSuccess(accessToken);
        }
        return new OAuth2SignupRequired(userInfo.getEmail());
    }

    public String getOAuth2LoginUrl(OAuth2ProviderType providerType) {
        String registrationId = providerType.name().toLowerCase();
        ClientRegistration clientRegistration = clientRegistrationRepository.findByRegistrationId(registrationId);
        if (clientRegistration == null) {
            throw new BadRequestException(ErrorCode.UNSUPPORTED_OAUTH2_PROVIDER);
        }
        // OAuth2 요청 파라미터 구성
        String authorizationUri = clientRegistration.getProviderDetails().getAuthorizationUri();
        String clientId = clientRegistration.getClientId();
        String redirectUri = clientRegistration.getRedirectUri();
        String scope = String.join(" ", clientRegistration.getScopes());
        String responseType = "code"; // OAuth2 인증 코드 요청

        // URL 인코딩 적용
        String encodedRedirectUri = URLEncoder.encode(redirectUri, StandardCharsets.UTF_8);
        String encodedScope = URLEncoder.encode(scope, StandardCharsets.UTF_8);

        // 최종 OAuth2 로그인 URL 생성
        return String.format("%s?client_id=%s&redirect_uri=%s&scope=%s&response_type=%s",
                authorizationUri, clientId, encodedRedirectUri, encodedScope, responseType);
    }
}
