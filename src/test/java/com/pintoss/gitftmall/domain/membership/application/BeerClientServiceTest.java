package com.pintoss.gitftmall.domain.membership.application;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BeerClientServiceTest {

    @Autowired
    BeerClientService beerClientService;

    @Test
    void test(){
        beerClientService.getBeer();
//        {"dataHeader":{"GW_RSLT_CD":"1200","GW_RSLT_MSG":"오류 없음"},"dataBody":{"access_token":"1b7a33cb-6c41-4a54-b583-5db46d0983ba","token_type":"bearer","expires_in":1565404419,"scope":"default"}}
//        {"dataHeader":{"GW_RSLT_CD":"1200","GW_RSLT_MSG":"오류 없음"},"dataBody":{"access_token":"1b7a33cb-6c41-4a54-b583-5db46d0983ba","token_type":"bearer","expires_in":1565404381,"scope":"default"}}
    }

}