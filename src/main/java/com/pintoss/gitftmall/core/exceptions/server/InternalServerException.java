package com.pintoss.gitftmall.core.exceptions.server;

import com.pintoss.gitftmall.core.exceptions.BaseException;
import com.pintoss.gitftmall.core.exceptions.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class InternalServerException extends BaseException {
    public InternalServerException(String message) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.INTERNAL_SERVER_ERROR, message);
    }
    public InternalServerException(ErrorCode errorCode, String message){
        super(HttpStatus.INTERNAL_SERVER_ERROR, errorCode, message);
    }
    public InternalServerException(ErrorCode errorCode){
        super(HttpStatus.INTERNAL_SERVER_ERROR, errorCode, errorCode.getMessage());
    }
}
