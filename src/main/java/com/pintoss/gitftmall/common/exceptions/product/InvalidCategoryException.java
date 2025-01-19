package com.pintoss.gitftmall.common.exceptions.product;

import com.pintoss.gitftmall.common.exceptions.ErrorCode;
import com.pintoss.gitftmall.common.exceptions.client.BadRequestException;

public class InvalidCategoryException extends BadRequestException {

    public InvalidCategoryException(String message) {
        super(message);
    }

    public InvalidCategoryException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
