package com.pintoss.gitftmall.common.exceptions.client;

import com.pintoss.gitftmall.common.exceptions.ErrorCode;
import lombok.Getter;

@Getter
public class ExpiredServerTokenException extends AuthorizationException {
    public ExpiredServerTokenException(String message){
        super(message);
    }

    public ExpiredServerTokenException(ErrorCode errorCode, String message){
        super(errorCode, message);
    }
}
