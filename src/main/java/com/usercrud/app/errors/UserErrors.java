package com.usercrud.app.errors;

public class UserErrors extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserErrors(String message) {
		super(message);
	}

	public UserErrors(Throwable cause) {
		super(cause);
	}

	public UserErrors() {
		super();
	}

	public UserErrors(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public UserErrors(String message, Throwable cause) {
		super(message, cause);
	}

}
