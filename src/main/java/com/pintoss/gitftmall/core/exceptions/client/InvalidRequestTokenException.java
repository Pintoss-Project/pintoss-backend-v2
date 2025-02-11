package com.pintoss.gitftmall.core.exceptions.client;

import com.pintoss.gitftmall.core.exceptions.ErrorCode;
import lombok.Getter;

@Getter
public class InvalidRequestTokenException extends AuthorizationException {
    public InvalidRequestTokenException(String message){
        super(message);
    }

    public InvalidRequestTokenException(ErrorCode errorCode, String message){
        super(errorCode, message);
    }
}
