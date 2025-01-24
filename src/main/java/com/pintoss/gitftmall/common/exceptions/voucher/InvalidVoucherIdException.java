package com.pintoss.gitftmall.common.exceptions.voucher;

import com.pintoss.gitftmall.common.exceptions.ErrorCode;
import com.pintoss.gitftmall.common.exceptions.client.BadRequestException;

public class InvalidVoucherIdException extends BadRequestException {

  public InvalidVoucherIdException(String message) {
    super(message);
  }

  public InvalidVoucherIdException(ErrorCode errorCode, String message) {
    super(errorCode, message);
  }
}
