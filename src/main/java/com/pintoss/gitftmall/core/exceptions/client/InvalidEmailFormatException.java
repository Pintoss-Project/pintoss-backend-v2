package com.pintoss.gitftmall.core.exceptions.client;

import com.pintoss.gitftmall.core.exceptions.ErrorCode;
import lombok.Getter;

@Getter
public class InvalidEmailFormatException extends BadRequestException {

    public InvalidEmailFormatException(String message){
        super(message);
    }

    public InvalidEmailFormatException(ErrorCode errorCode, String message){
        super(errorCode, message);
    }
}
