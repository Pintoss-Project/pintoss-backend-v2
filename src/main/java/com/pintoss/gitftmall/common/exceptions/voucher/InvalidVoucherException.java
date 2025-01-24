package com.pintoss.gitftmall.common.exceptions.voucher;

import com.pintoss.gitftmall.common.exceptions.ErrorCode;
import com.pintoss.gitftmall.common.exceptions.client.BadRequestException;

public class InvalidVoucherException extends BadRequestException {

  public InvalidVoucherException(String message) {
    super(message);
  }

  public InvalidVoucherException(ErrorCode errorCode, String message) {
    super(errorCode, message);
  }
}
