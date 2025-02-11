package com.pintoss.gitftmall.core.exceptions.client;


import com.pintoss.gitftmall.core.exceptions.ErrorCode;

public class EmptyURLException extends BadRequestException {
    public EmptyURLException(String message){
        super(message);
    }

    public EmptyURLException(ErrorCode errorCode, String message){
        super(errorCode, message);
    }
}
