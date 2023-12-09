package com.demo.exception;

public class BankNotFoundException extends Exception {
	public BankNotFoundException() {

	}

	public BankNotFoundException(String message) {
		super(message);
	}

	public BankNotFoundException(Throwable cause) {
		super(cause);
	}

	public BankNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
