package com.pintoss.gitftmall.infra.security.dto;

import com.pintoss.gitftmall.domain.membership.model.User;
import com.pintoss.gitftmall.domain.membership.model.value.Email;
import com.pintoss.gitftmall.domain.membership.model.value.OAuthProvider;
import com.pintoss.gitftmall.domain.membership.model.value.RoleEnum;
import com.pintoss.gitftmall.domain.membership.model.value.UserRole;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;
import java.util.Set;

@Getter
public class OAuthAttributes {
    private final Map<String, Object> attributes;
    private final String nameAttributeKey;
    private final String name;
    private final Email email;
    private final OAuthProvider provider;

    @Builder
    public OAuthAttributes(
            Map<String, Object> attributes, String nameAttributeKey, String name, String email, OAuthProvider provider
    ) {
      this.attributes = attributes;
      this.nameAttributeKey = nameAttributeKey;
      this.name = name;
      this.email = new Email(email);
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
        switch (registrationId.toLowerCase()) {
            case "kakao":
                return OAuthProvider.KAKAO;
            case "naver":
                return OAuthProvider.NAVER;
            default:
                return OAuthProvider.NONE;
        }
    }

    private static OAuthAttributes ofKakao(
            String userNameAttributeName,
            Map<String, Object> attributes,
            OAuthProvider provider
    ) {

        Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
        Map<String, Object> properties = (Map<String, Object>) attributes.get("properties");

        String nickname = (String) properties.get("nickname"); // 닉네임은 properties에서 가져옴
        String email = (String) kakaoAccount.get("email");     // 이메일은 kakao_account에서 가져옴

        return OAuthAttributes.builder()
                .name(nickname)
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

        String name = (String) response.get("name");
        String email = (String) response.get("email");

        return OAuthAttributes.builder()
                .name(name)
                .email(email)
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .provider(provider)
                .build();
    }

    public User toEntity() {
        return User.createOAuthUser(
                email, name, Set.of(new UserRole(RoleEnum.USER)), provider
        );
    }
}
