package com.pintoss.gitftmall.core.exceptions.client;

import com.pintoss.gitftmall.core.exceptions.ErrorCode;
import lombok.Getter;

@Getter
public class InvalidPhoneFormatException extends BadRequestException {
    public InvalidPhoneFormatException(String message){
        super(message);
    }

    public InvalidPhoneFormatException(ErrorCode errorCode, String message){
        super(errorCode, message);
    }
}
