package com.pintoss.gitftmall.domain.membership.model.value;

public enum OAuthProvider {
    NONE("none"),
    KAKAO("kakao"),
    NAVER("naver");

    private final String value;

    OAuthProvider(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static OAuthProvider fromValue(String value) {
        for (OAuthProvider provider : OAuthProvider.values()) {
            if (provider.getValue().equalsIgnoreCase(value)) {
                return provider;
            }
        }
        throw new IllegalArgumentException();
    }
}
