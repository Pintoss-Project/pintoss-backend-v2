package com.pintoss.gitftmall.common.exceptions.voucher;

import com.pintoss.gitftmall.common.exceptions.ErrorCode;
import com.pintoss.gitftmall.common.exceptions.client.BadRequestException;

public class InvalidVoucherProviderIdException extends BadRequestException {

    public InvalidVoucherProviderIdException(String message) {
        super(message);
    }

    public InvalidVoucherProviderIdException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
