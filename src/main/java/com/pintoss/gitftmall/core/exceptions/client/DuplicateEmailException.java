package com.pintoss.gitftmall.core.exceptions.client;

import com.pintoss.gitftmall.core.exceptions.ErrorCode;
import lombok.Getter;

@Getter
public class DuplicateEmailException extends BadRequestException {
    public DuplicateEmailException(String message){
        super(message);
    }

    public DuplicateEmailException(ErrorCode errorCode, String message){
        super(errorCode, message);
    }
}
