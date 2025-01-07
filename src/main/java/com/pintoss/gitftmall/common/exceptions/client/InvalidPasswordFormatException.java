package com.pintoss.gitftmall.common.exceptions.client;

import com.pintoss.gitftmall.common.exceptions.ErrorCode;
import lombok.Getter;

@Getter
public class InvalidPasswordFormatException extends BadRequestException {

    public InvalidPasswordFormatException(String message){
        super(message);
    }

    public InvalidPasswordFormatException(ErrorCode errorCode, String message){
        super(errorCode, message);
    }
}
