package com.hts.exceptions;

public class AppException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String message;

	public AppException(String message) {
		this.message = message;
		
	}

	@Override
	public String getMessage() {
		return this.message;
	}
}