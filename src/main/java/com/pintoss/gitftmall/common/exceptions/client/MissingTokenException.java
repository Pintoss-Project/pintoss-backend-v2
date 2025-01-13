package com.pintoss.gitftmall.common.exceptions.client;

import com.pintoss.gitftmall.common.exceptions.ErrorCode;
import lombok.Getter;

@Getter
public class MissingTokenException extends AuthorizationException {
    public MissingTokenException(String message){
        super(message);
    }

    public MissingTokenException(ErrorCode errorCode, String message){
        super(errorCode, message);
    }

}
