package com.pintoss.gitftmall.core.exceptions.client;

import com.pintoss.gitftmall.core.exceptions.ErrorCode;
import lombok.Getter;

@Getter
public class DuplicatePhoneException extends BadRequestException {
    public DuplicatePhoneException(String message){
        super(message);
    }

    public DuplicatePhoneException(ErrorCode errorCode, String message){
        super(errorCode, message);
    }
}
