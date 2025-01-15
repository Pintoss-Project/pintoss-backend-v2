package com.pintoss.gitftmall.domain.membership.application;

import com.pintoss.gitftmall.infra.nice.service.NiceAuthService;
import com.pintoss.gitftmall.infra.nice.client.request.NiceApiAccessTokenResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NiceAuthServiceTest {

    @Autowired
    NiceAuthService niceAuthService;

    @Test
    void getAccessToken(){
        NiceApiAccessTokenResult accessTokenTest = niceAuthService.getAccessTokenTest();

        System.out.println(accessTokenTest.getDataBody().getExpires_in());
    }

    @Test
    void getEncryptedToken() throws Exception{
        niceAuthService.getEncryptedToken();
    }

}