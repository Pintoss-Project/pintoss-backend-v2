package com.pintoss.gitftmall.common.exceptions.client;

import com.pintoss.gitftmall.common.exceptions.BaseException;
import com.pintoss.gitftmall.common.exceptions.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BadRequestException extends BaseException {
    public BadRequestException(String message){
        super(HttpStatus.BAD_REQUEST, ErrorCode.BAD_REQUEST, message);
    }

    public BadRequestException(ErrorCode errorCode, String message){
        super(HttpStatus.BAD_REQUEST, errorCode, message);
    }

}
