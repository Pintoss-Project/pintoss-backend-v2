package com.pintoss.gitftmall.core.exceptions.client;

import com.pintoss.gitftmall.core.exceptions.ErrorCode;

public class InvalidURLException extends BadRequestException {
    public InvalidURLException(String message){
        super(message);
    }

    public InvalidURLException(ErrorCode errorCode, String message){
        super(errorCode, message);
    }
}
