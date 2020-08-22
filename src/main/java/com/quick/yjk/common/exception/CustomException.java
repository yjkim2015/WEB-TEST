package com.quick.yjk.common.exception;

public class CustomException extends Exception {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 생성자
	 * @param errorMsg
	 */
	public CustomException(final String errorMsg) {
		this(errorMsg, null);
	}
	
	/**
	 * 생성자
	 * @param errorMsg
	 * @param throwable
	 */
	public CustomException(final String errorMsg, final Throwable throwable) {
		super(errorMsg, throwable);
	}
}
