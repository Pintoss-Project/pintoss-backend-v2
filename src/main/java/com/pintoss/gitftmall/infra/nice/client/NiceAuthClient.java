package com.pintoss.gitftmall.infra.nice.client;

import com.pintoss.gitftmall.domain.membership.application.command.AccessTokenResult;
import com.pintoss.gitftmall.domain.membership.application.command.EncryptedRequest;
import com.pintoss.gitftmall.domain.membership.application.result.EncryptedResult;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Base64;
import java.util.Date;

@Component
public class NiceAuthClient {

    private final WebClient webClient;

    public NiceAuthClient(@Qualifier("niceWebClient") WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<AccessTokenResult> getAccessToken(String uri, String clientId, String clientSecret) {
        String authorizationHeader = "Basic " + Base64.getEncoder()
                .encodeToString((clientId + ":" + clientSecret).getBytes());

        return webClient.post()
                .uri(uri)
                .header("Authorization", authorizationHeader)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .bodyValue("grant_type=client_credentials&scope=default")
                .retrieve()
                .bodyToMono(AccessTokenResult.class);
    }

    public Mono<EncryptedResult> getEncryptedToken(String uri, String accessToken, String clientId, String productId, EncryptedRequest encryptedRequest) {
        String authorizationHeader = "bearer " + Base64.getEncoder()
                .encodeToString((String.valueOf(accessToken+":"+ (new Date().getTime() / 1000)+":"+clientId)).getBytes());

        return webClient.post()
                .uri(uri)
                .header("Authorization", authorizationHeader)
                .header("ProductID", productId)
                .header("Content-Type", "application/json")
                .body(Mono.just(encryptedRequest), EncryptedRequest.class)
                .retrieve()
                .bodyToMono(EncryptedResult.class);
    }
}
