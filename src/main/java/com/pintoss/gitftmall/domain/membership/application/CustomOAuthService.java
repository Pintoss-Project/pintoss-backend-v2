package com.pintoss.gitftmall.domain.membership.application;

import com.pintoss.gitftmall.domain.membership.model.User;
import com.pintoss.gitftmall.domain.membership.repository.IUserRepository;
import com.pintoss.gitftmall.infra.security.oauth.dto.OAuthAttributes;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class CustomOAuthService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final IUserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = getOAuth2User(userRequest);

        OAuthAttributes attributes = getOAuthAttributes(userRequest, oAuth2User);
        Long userId = getOrCreate(attributes);

        return createDefaultOAuth2User(userId, attributes);
    }

    private OAuth2User getOAuth2User(OAuth2UserRequest userRequest) {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        return delegate.loadUser(userRequest);
    }

    private OAuthAttributes getOAuthAttributes(OAuth2UserRequest userRequest, OAuth2User oAuth2User) {
        // 현재 로그인 진행중인 서비스
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        // OAuth2 로그인 진행시 키가 되는 필드값
        String userNameAttributeName = userRequest.getClientRegistration()
                .getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

        return OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());
    }

    private Long getOrCreate(OAuthAttributes attributes) {
        return userRepository.findByEmail(attributes.getEmail())
                .map(User::getId)
                .orElseGet(() -> {
                    User newUser = attributes.toEntity();
                    return userRepository.save(newUser);
                });
    }

    private DefaultOAuth2User createDefaultOAuth2User(Long userId, OAuthAttributes attributes) {
        Map<String, Object> updatedAttributes = new HashMap<>(attributes.getAttributes());

        updatedAttributes.put("userId", userId);
        updatedAttributes.put("email", attributes.getEmail());
        updatedAttributes.put("password", attributes.getPassword());

        return new DefaultOAuth2User(
                Collections.emptySet(),
                updatedAttributes,
                attributes.getNameAttributeKey()
        );
    }
}
