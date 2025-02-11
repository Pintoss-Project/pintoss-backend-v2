package com.pintoss.gitftmall.domain.membership.infra.nice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class NiceApiConfig {

    @Bean(name="niceWebClient")
    public WebClient niceWebClient(WebClient.Builder builder) {
        return builder
                .baseUrl("https://svc.niceapi.co.kr:22001")
                .build();
    }
}
