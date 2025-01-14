package com.pintoss.gitftmall.common.exceptions.client;

import com.pintoss.gitftmall.common.exceptions.ErrorCode;
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
