package com.pintoss.gitftmall.common.exceptions.client;

import com.pintoss.gitftmall.common.exceptions.ErrorCode;
import lombok.Getter;

@Getter
public class EmptyEmailException extends BadRequestException {
    public EmptyEmailException(String message){
        super(message);
    }

    public EmptyEmailException(ErrorCode errorCode, String message){
        super(errorCode, message);
    }
}
