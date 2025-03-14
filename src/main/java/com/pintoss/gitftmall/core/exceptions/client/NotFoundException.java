package com.pintoss.gitftmall.core.exceptions.client;

import com.pintoss.gitftmall.core.exceptions.BaseException;
import com.pintoss.gitftmall.core.exceptions.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class NotFoundException extends BaseException {

    public NotFoundException(ErrorCode errorCode, String message){
        super(HttpStatus.BAD_REQUEST, errorCode, message);
    }

    public NotFoundException(ErrorCode errorCode){
        super(HttpStatus.BAD_REQUEST, errorCode, errorCode.getMessage());
    }
}
