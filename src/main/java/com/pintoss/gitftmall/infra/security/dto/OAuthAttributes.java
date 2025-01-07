package com.pintoss.gitftmall.infra.security.dto;

import com.pintoss.gitftmall.domain.membership.model.User;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuthAttributes {
    private final Map<String, Object> attributes;
    private final String nameAttributeKey;
    private final String name;
    private final String email;

    @Builder
    public OAuthAttributes(
            Map<String, Object> attributes, String nameAttributeKey, String name, String email
    ) {
      this.attributes = attributes;
      this.nameAttributeKey = nameAttributeKey;
      this.name = name;
      this.email = email;
    }

    public static OAuthAttributes of(
            String registrationId, String userNameAttributeName, Map<String, Object> attributes
    ) {
        if ("kakao".equalsIgnoreCase(registrationId)) {
            return ofKakao(userNameAttributeName, attributes);
        } else if ("naver".equalsIgnoreCase(registrationId)) {
            return ofNaver(userNameAttributeName, attributes);
        }
        throw new IllegalArgumentException("Unsupported registrationId: " + registrationId);
    }

    private static OAuthAttributes ofKakao(
            String userNameAttributeName,
            Map<String, Object> attributes
    ) {
        return OAuthAttributes.builder()
                .name((String) attributes.get("profile_nickname"))
                .email((String) attributes.get("account_email"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    private static OAuthAttributes ofNaver(
            String userNameAttributeName,
            Map<String, Object> attributes
    ) {
        return OAuthAttributes.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }
}
