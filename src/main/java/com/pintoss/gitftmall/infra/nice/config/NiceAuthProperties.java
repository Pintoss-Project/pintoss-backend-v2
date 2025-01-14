package com.pintoss.gitftmall.infra.nice.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@ConfigurationProperties("nice-auth")
@Configuration
public class NiceAuthProperties {
    private String clientId;
    private String clientSecret;
    private String accessTokenUri;
    private String encryptedTokenUri;
    private String redirectUri;
    private String productId;
}
