package com.pintoss.gitftmall.core.exceptions.client;

import com.pintoss.gitftmall.core.exceptions.ErrorCode;
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
