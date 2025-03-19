package com.pintoss.gitftmall.domain.membership.infra.security.oauth.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.pintoss.gitftmall.core.exceptions.ErrorCode;
import com.pintoss.gitftmall.core.exceptions.client.BadRequestException;
import com.pintoss.gitftmall.domain.membership.domain.vo.LoginType;
import com.pintoss.gitftmall.domain.membership.infra.security.oauth.client.dto.NaverUserInfoResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class NaverApiClient {

    private final WebClient webClient;
    private final ClientRegistrationRepository clientRegistrationRepository;

    public NaverApiClient(WebClient.Builder webClientBuilder, ClientRegistrationRepository clientRegistrationRepository) {
        this.webClient = webClientBuilder
                .baseUrl("https://nid.naver.com")
                .build();
        this.clientRegistrationRepository = clientRegistrationRepository;
    }

    public String getAccessToken(String code) {
        ClientRegistration clientRegistration = clientRegistrationRepository.findByRegistrationId(LoginType.NAVER.getValue());
        if (clientRegistration == null) {
            throw new BadRequestException(ErrorCode.UNSUPPORTED_OAUTH2_PROVIDER);
        }

        return this.webClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path("/oauth2.0/token")
                        .queryParam("grant_type", "authorization_code")
                        .queryParam("client_id", clientRegistration.getClientId())
                        .queryParam("client_secret", clientRegistration.getClientSecret())
                        .queryParam("code", code)
                        .build())
                .retrieve()
                .bodyToMono(JsonNode.class)
                .map(response -> response.get("access_token").asText())
                .block();

    }

    public NaverUserInfoResponse getUserInfo(String accessToken) {
        ClientRegistration clientRegistration = clientRegistrationRepository.findByRegistrationId(LoginType.NAVER.getValue());
        if (clientRegistration == null) {
            throw new BadRequestException(ErrorCode.UNSUPPORTED_OAUTH2_PROVIDER);
        }

        return this.webClient.get()
                .uri("https://openapi.naver.com/v1/nid/me")
                .header(HttpHeaders.AUTHORIZATION, "Bearer "+ accessToken)
                .retrieve()
                .bodyToMono(NaverUserInfoResponse.class)
                .block();
    }
}
