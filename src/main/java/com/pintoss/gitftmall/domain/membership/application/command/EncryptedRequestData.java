package com.pintoss.gitftmall.domain.membership.application.command;

import lombok.Data;

@Data
public class EncryptedRequestData {

    private String requestno;
    private String returnurl;
    private String sitecode;
    private String authtype;
    private String mobilceco;
    private String businessno;
    private String methodtype;
    private String popupyn;
    private String receiverdata;

    public EncryptedRequestData(String requestno, String returnurl, String sitecode) {
        this.requestno = requestno;
        this.returnurl = returnurl;
        this.sitecode = sitecode;
        this.methodtype = "get";
        this.popupyn = "Y";
    }
}
