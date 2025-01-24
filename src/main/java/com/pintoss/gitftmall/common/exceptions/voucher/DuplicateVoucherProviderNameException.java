package com.pintoss.gitftmall.common.exceptions.voucher;

import com.pintoss.gitftmall.common.exceptions.ErrorCode;
import com.pintoss.gitftmall.common.exceptions.client.BadRequestException;
import lombok.Getter;

@Getter
public class DuplicateVoucherProviderNameException extends BadRequestException {
    public DuplicateVoucherProviderNameException(String message){
        super(message);
    }

    public DuplicateVoucherProviderNameException(ErrorCode errorCode, String message){
        super(errorCode, message);
    }
}
