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
    private final String name;
    private final String email;
    private final String password;
    private final OAuthProvider provider;

    private static final String BASE_PASSWORD = "Oauth0!";
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Builder
    public OAuthAttributes(
            Map<String, Object> attributes, String nameAttributeKey, String name,
            String email, String password, OAuthProvider provider
    ) {
      this.attributes = attributes;
      this.nameAttributeKey = nameAttributeKey;
      this.name = name;
      this.email = email;
      this.password = password;
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
        Map<String, Object> properties = (Map<String, Object>) attributes.get("properties");

        String nickname = (String) properties.get("nickname"); // 닉네임은 properties에서 가져옴
        String email = (String) kakaoAccount.get("email");     // 이메일은 kakao_account에서 가져옴

        return OAuthAttributes.builder()
                .name(nickname)
                .email(email)
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .provider(provider)
                .password(PasswordGenerator())
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
                .password(PasswordGenerator())
                .build();
    }

    public User toEntity() {
        String dummyPassword = PasswordGenerator();
        Phone dummyPhone = new Phone("010-0000-0000");

        return User.create(
                new Email(email), dummyPassword, name, dummyPhone, Set.of(new UserRole(RoleEnum.USER)), encoder, provider);
    }

    public static String PasswordGenerator() {
        String uuid = UUID.randomUUID().toString().replace("-", "");

        return BASE_PASSWORD + uuid.substring(0, 13);
    }
}
