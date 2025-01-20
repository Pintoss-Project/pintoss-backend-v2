package com.pintoss.gitftmall.infra.security.oauth.dto;

import com.pintoss.gitftmall.domain.membership.application.command.LoginServiceCommand;
import com.pintoss.gitftmall.domain.membership.application.result.LoginResult;
import com.pintoss.gitftmall.domain.membership.model.User;
import com.pintoss.gitftmall.domain.membership.model.value.*;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Getter
public class OAuthAttributes {
    private final Map<String, Object> attributes;
    private final String nameAttributeKey;
    private final String email;
    private final OAuthProvider provider;

    @Builder
    public OAuthAttributes(
            Map<String, Object> attributes, String nameAttributeKey,
            String email, OAuthProvider provider
    ) {
      this.attributes = attributes;
      this.nameAttributeKey = nameAttributeKey;
      this.email = email;
      this.provider = provider;
    }

    public static OAuthAttributes of(
            String registrationId, String userNameAttributeName, Map<String, Object> attributes
    ) {
        OAuthProvider provider = convertToProvider(registrationId);
        if (provider == OAuthProvider.KAKAO) {
            return ofKakao(userNameAttributeName, attributes, provider);
        } else if (provider == OAuthProvider.NAVER) {
            return ofNaver(userNameAttributeName, attributes, provider);
        }
        throw new IllegalArgumentException("Unsupported registrationId: " + registrationId);
    }

    private static OAuthProvider convertToProvider(String registrationId) {
        return OAuthProvider.fromValue(registrationId);
    }

    private static OAuthAttributes ofKakao(
            String userNameAttributeName,
            Map<String, Object> attributes,
            OAuthProvider provider
    ) {

        Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");

        String email = (String) kakaoAccount.get("email");

        return OAuthAttributes.builder()
                .email(email)
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .provider(provider)
                .build();
    }

    private static OAuthAttributes ofNaver(
            String userNameAttributeName,
            Map<String, Object> attributes,
            OAuthProvider provider
    ) {

        Map<String, Object> response = (Map<String, Object>) attributes.get("response");

        String email = (String) response.get("email");

        return OAuthAttributes.builder()
                .email(email)
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .provider(provider)
                .build();
    }
}
