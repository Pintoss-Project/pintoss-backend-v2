package com.pintoss.gitftmall.common.exceptions.client;

import com.pintoss.gitftmall.common.exceptions.BaseException;
import com.pintoss.gitftmall.common.exceptions.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class AuthorizationException extends BaseException {
    public AuthorizationException(String message) {
        super(HttpStatus.UNAUTHORIZED, ErrorCode.UNAUTHORIZED, message);
    }
    public AuthorizationException(ErrorCode errorCode, String message){
        super(HttpStatus.BAD_REQUEST, errorCode, message);
    }
}
