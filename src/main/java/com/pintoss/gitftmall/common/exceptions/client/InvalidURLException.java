package com.pintoss.gitftmall.common.exceptions.client;

import com.pintoss.gitftmall.common.exceptions.ErrorCode;

public class InvalidURLException extends BadRequestException {
    public InvalidURLException(String message){
        super(message);
    }

    public InvalidURLException(ErrorCode errorCode, String message){
        super(errorCode, message);
    }
}
