package com.pintoss.gitftmall.common.exceptions.client;

import com.pintoss.gitftmall.common.exceptions.ErrorCode;

public class EmptyURLException extends BadRequestException {
    public EmptyURLException(String message){
        super(message);
    }

    public EmptyURLException(ErrorCode errorCode, String message){
        super(errorCode, message);
    }
}
