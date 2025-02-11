package com.pintoss.gitftmall.core.exceptions.client;

import com.pintoss.gitftmall.core.exceptions.ErrorCode;
import lombok.Getter;

@Getter
public class DuplicateProductNameException extends BadRequestException {
    public DuplicateProductNameException(String message){
        super(message);
    }

    public DuplicateProductNameException(ErrorCode errorCode, String message){
        super(errorCode, message);
    }
}
