package com.softline.common;

/**
 * 
 * @author zxt
 * @since 2018-02-24
 */
public class ValidatorException extends RuntimeException {

	public ValidatorException(String msg) {
		super(msg);
	}

	public ValidatorException(String message, Throwable cause) {
		super(message, cause);
	}

}
