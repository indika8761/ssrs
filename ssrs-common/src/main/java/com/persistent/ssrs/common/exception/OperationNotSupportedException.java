package com.persistent.ssrs.common.exception;

public class OperationNotSupportedException extends RuntimeException {

	private static final long serialVersionUID = 3029631494138400588L;

	public OperationNotSupportedException() {
		super();
	}

	public OperationNotSupportedException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public OperationNotSupportedException(String message, Throwable cause) {
		super(message, cause);
	}

	public OperationNotSupportedException(String message) {
		super(message);
	}

	public OperationNotSupportedException(Throwable cause) {
		super(cause);
	}

}
