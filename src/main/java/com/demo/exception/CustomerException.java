package com.demo.exception;

public class CustomerException extends Exception {
	public CustomerException() {
		super();
	}

	public CustomerException(String message) {
		super(message);
	}

	public CustomerException(Throwable cause) {
		super(cause);
	}

	public CustomerException(String message, Throwable cause) {
		super(message, cause);
	}
}
