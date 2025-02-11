package com.pintoss.gitftmall.core.exceptions.client;

import com.pintoss.gitftmall.core.exceptions.ErrorCode;
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
