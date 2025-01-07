package com.pintoss.gitftmall.common.exceptions.client;

import com.pintoss.gitftmall.common.exceptions.ErrorCode;
import lombok.Getter;

@Getter
public class EmptyPhoneException extends BadRequestException {
    public EmptyPhoneException(String message){
        super(message);
    }

    public EmptyPhoneException(ErrorCode errorCode, String message){
        super(errorCode, message);
    }
}
