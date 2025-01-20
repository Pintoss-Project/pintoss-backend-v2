package com.pintoss.gitftmall.domain.membership.application;

import com.pintoss.gitftmall.common.exceptions.client.CustomOAuthAuthenticationException;
import com.pintoss.gitftmall.domain.membership.model.User;
import com.pintoss.gitftmall.domain.membership.repository.IUserRepository;
import com.pintoss.gitftmall.infra.security.oauth.dto.OAuthAttributes;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CustomOAuthService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final IUserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws CustomOAuthAuthenticationException {
        OAuth2User oAuth2User = getOAuth2User(userRequest);

        OAuthAttributes attributes = getOAuthAttributes(userRequest, oAuth2User);

        Optional<User> existUser = getUserByEmail(attributes.getEmail());

        return createDefaultOAuth2User(userRequest, existUser, attributes);
    }

    private OAuth2User getOAuth2User(OAuth2UserRequest userRequest) {
        try {
            OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
            return delegate.loadUser(userRequest);
        } catch (Exception e) {
            throw new CustomOAuthAuthenticationException("OAuth2 사용자 정보를 가져오는 중 문제가 발생했습니다");
        }
    }

    private OAuthAttributes getOAuthAttributes(OAuth2UserRequest userRequest, OAuth2User oAuth2User) {
        try {
            // 현재 로그인 진행중인 서비스
            String registrationId = userRequest.getClientRegistration().getRegistrationId();
            // OAuth2 로그인 진행시 키가 되는 필드값
            String userNameAttributeName = userRequest.getClientRegistration()
                    .getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

            return OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());
        } catch (Exception e) {
            throw new CustomOAuthAuthenticationException("OAuth2 사용자 속성 매핑하는 과정에서 문제가 발생했습니다");

        }
    }

    private Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    private DefaultOAuth2User createDefaultOAuth2User(OAuth2UserRequest userRequest, Optional<User> user, OAuthAttributes attributes) {
        try {
            Map<String, Object> updatedAttributes = new HashMap<>(attributes.getAttributes());

            updatedAttributes.put("email", attributes.getEmail());
            updatedAttributes.put("provider", userRequest.getClientRegistration().getRegistrationId());

            user.ifPresent(value -> updatedAttributes.put("userId", value.getId()));

            return new DefaultOAuth2User(
                    Collections.emptySet(),
                    updatedAttributes,
                    attributes.getNameAttributeKey()
            );
        } catch (Exception e) {
            throw new CustomOAuthAuthenticationException("DefaultOAuth2User 생성 중 문제가 발생했습니다");
        }
    }
}
