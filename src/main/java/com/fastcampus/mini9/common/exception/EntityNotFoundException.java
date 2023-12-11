package com.fastcampus.mini9.common.exception;

public class EntityNotFoundException extends BaseException{
	public EntityNotFoundException() {
		super(ErrorCode.EntityNotFound.getMsg(), ErrorCode.EntityNotFound);
	}

	public EntityNotFoundException(String message) {
		super(message, ErrorCode.EntityNotFound);
	}

	public EntityNotFoundException(String message, ErrorCode errorCode) {
		super(message, errorCode);
	}
}
