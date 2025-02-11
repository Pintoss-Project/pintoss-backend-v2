package com.pintoss.gitftmall.core.exceptions.client;

import com.pintoss.gitftmall.core.exceptions.ErrorCode;
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
