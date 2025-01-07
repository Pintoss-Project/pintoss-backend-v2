package com.pintoss.gitftmall.common.exceptions.client;

import com.pintoss.gitftmall.common.exceptions.ErrorCode;
import lombok.Getter;

@Getter
public class EmptyPasswordException extends BadRequestException {
    public EmptyPasswordException(String message){
        super(message);
    }

    public EmptyPasswordException(ErrorCode errorCode, String message){
        super(errorCode, message);
    }
}
