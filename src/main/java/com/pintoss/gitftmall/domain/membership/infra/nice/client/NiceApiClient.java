package com.pintoss.gitftmall.domain.membership.infra.nice.client;

import com.pintoss.gitftmall.domain.membership.infra.nice.client.response.NiceApiAccessTokenResponse;
import com.pintoss.gitftmall.domain.membership.infra.nice.client.request.NiceApiCryptoTokenRequest;
import com.pintoss.gitftmall.domain.membership.infra.nice.client.response.NiceApiCryptoTokenResponse;
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

    public Mono<NiceApiAccessTokenResponse> getAccessToken(String uri, String clientId, String clientSecret) {
        String authorizationHeader = "Basic " + Base64.getEncoder()
                .encodeToString((clientId + ":" + clientSecret).getBytes());
        System.out.println(authorizationHeader);
        return webClient.post()
                .uri(uri)
                .header("Authorization", authorizationHeader)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .bodyValue("grant_type=client_credentials&scope=default")
                .retrieve()
                .bodyToMono(NiceApiAccessTokenResponse.class);
    }

    public Mono<NiceApiCryptoTokenResponse> getCryptoToken(String uri, String accessToken, String clientId, String productId, NiceApiCryptoTokenRequest niceApiCryptoTokenRequest) {
        String authorizationHeader = "bearer " + Base64.getEncoder()
                .encodeToString((String.valueOf(accessToken+":"+ (new Date().getTime() / 1000)+":"+clientId)).getBytes());

        return webClient.post()
                .uri(uri)
                .header("Authorization", authorizationHeader)
                .header("ProductID", productId)
                .header("Content-Type", "application/json")
                .body(Mono.just(niceApiCryptoTokenRequest), NiceApiCryptoTokenRequest.class)
                .retrieve()
                .bodyToMono(NiceApiCryptoTokenResponse.class);
    }
}
