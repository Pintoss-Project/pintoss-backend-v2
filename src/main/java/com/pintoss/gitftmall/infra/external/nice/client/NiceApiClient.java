package com.pintoss.gitftmall.infra.external.nice.client;

import com.pintoss.gitftmall.infra.external.nice.client.request.NiceApiAccessTokenResult;
import com.pintoss.gitftmall.infra.external.nice.client.request.NiceApiEncryptedTokenRequest;
import com.pintoss.gitftmall.infra.external.nice.client.response.NiceApiEncryptedTokenResult;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Base64;
import java.util.Date;

@Component
public class NiceApiClient {

    private final WebClient webClient;

    public NiceApiClient(@Qualifier("niceWebClient") WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<NiceApiAccessTokenResult> getAccessToken(String uri, String clientId, String clientSecret) {
        String authorizationHeader = "Basic " + Base64.getEncoder()
                .encodeToString((clientId + ":" + clientSecret).getBytes());
        System.out.println(authorizationHeader);
        return webClient.post()
                .uri(uri)
                .header("Authorization", authorizationHeader)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .bodyValue("grant_type=client_credentials&scope=default")
                .retrieve()
                .bodyToMono(NiceApiAccessTokenResult.class);
    }

    public Mono<NiceApiEncryptedTokenResult> getEncryptedToken(String uri, String accessToken, String clientId, String productId, NiceApiEncryptedTokenRequest niceApiEncryptedTokenRequest) {
        String authorizationHeader = "bearer " + Base64.getEncoder()
                .encodeToString((String.valueOf(accessToken+":"+ (new Date().getTime() / 1000)+":"+clientId)).getBytes());

        return webClient.post()
                .uri(uri)
                .header("Authorization", authorizationHeader)
                .header("ProductID", productId)
                .header("Content-Type", "application/json")
                .body(Mono.just(niceApiEncryptedTokenRequest), NiceApiEncryptedTokenRequest.class)
                .retrieve()
                .bodyToMono(NiceApiEncryptedTokenResult.class);
    }
}
