package com.pintoss.gitftmall.common.exceptions.client;

import com.pintoss.gitftmall.common.exceptions.BaseException;
import com.pintoss.gitftmall.common.exceptions.ErrorCode;
import org.springframework.http.HttpStatus;

public class NotFoundMemberException extends BaseException {
    public NotFoundMemberException(String message) {
        super(HttpStatus.NOT_FOUND, ErrorCode.NOT_FOUND, message);
    }
}
