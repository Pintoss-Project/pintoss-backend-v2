package com.pintoss.gitftmall.domain.membership.application;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Base64;

@Service
@Slf4j
public class BeerClientService {
    private String clientId = "f93f0663-aacc-409b-a7e9-0577d4c42cea:edf7656c1e821103f4a407204bfcf39f";
    private String clientSecret = "edf7656c1e821103f4a407204bfcf39f";
    public void getBeer() {
        // WebClient는 Builder 패턴 처럼 사용
        WebClient webClient = WebClient.builder().build();
        String url = "https://svc.niceapi.co.kr:22001/digital/niceid/oauth/oauth/token?grant_type=client_credentials&scope=default";
        // 어떤 HTTP 메소드로 요청 보낼지를 get(), post() 메소드 등으로 결정
        // 만일 다른 메소드를 쓰고 싶다면, method()
        String authorizationHeader = "Basic " + Base64.getEncoder()
                .encodeToString((clientId).getBytes());
        String response = webClient.post()
                .uri("https://svc.niceapi.co.kr:22001/digital/niceid/oauth/oauth/token?grant_type=client_credentials&scope=default")
                .header("Authorization", authorizationHeader)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .bodyValue("grant_type=client_credentials&scope=default")
                .retrieve()
                .bodyToMono(String.class)
                .block();
//        String response = webClient.post() // webClient.method(HttpMethod.GET)
//                .uri(url)	// 요청 경로 설정
//                .header("Content-Type", "application/x-www-form-urlencoded")
//                .header("Authorization", "Basic "+ Base64.getEncoder().encodeToString(clientId.getBytes())) // 요청 헤더 추가
//                .bodyValue("grant_type=client_credentials&scope=default")
//                // body도 메소드에 따라 추가
//                .retrieve()	// 여기 전까지가 요청을 정의 한 부분
//                // 여기부터 정의하는건 응답을 어떻게 처리할 것인지
//                .bodyToMono(String.class)	// 응답이 한번 돌아오고, 응답의 body를 String으로 해석
//                .block();	// 동기식으로 처리
        log.info(response);
        System.out.println(response);
    }
}