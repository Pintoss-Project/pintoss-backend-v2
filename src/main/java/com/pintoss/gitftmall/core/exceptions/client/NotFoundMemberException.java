package com.pintoss.gitftmall.core.exceptions.client;

import com.pintoss.gitftmall.core.exceptions.BaseException;
import com.pintoss.gitftmall.core.exceptions.ErrorCode;
import org.springframework.http.HttpStatus;

public class NotFoundMemberException extends BaseException {
    public NotFoundMemberException(String message) {
        super(HttpStatus.NOT_FOUND, ErrorCode.NOT_FOUND, message);
    }
}
