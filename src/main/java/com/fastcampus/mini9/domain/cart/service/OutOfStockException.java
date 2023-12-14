package com.fastcampus.mini9.domain.cart.service;

import com.fastcampus.mini9.common.exception.BaseException;
import com.fastcampus.mini9.common.exception.ErrorCode;

public class OutOfStockException extends BaseException {
	public OutOfStockException(ErrorCode errorCode) {
		super(errorCode);
	}

	public OutOfStockException(String message, ErrorCode errorCode) {
		super(message, errorCode);
	}

	public OutOfStockException(String message, Throwable cause, ErrorCode errorCode) {
		super(message, cause, errorCode);
	}

	public OutOfStockException(Throwable cause, ErrorCode errorCode) {
		super(cause, errorCode);
	}

	@Override
	public String getMessage() {
		return errorCode.getMsg();
	}
}
