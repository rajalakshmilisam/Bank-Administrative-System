package com.demo.exception;

public class BankException extends Exception {
	public BankException() {
		super();
	}

	public BankException(String message) {
		super(message);
	}

	public BankException(Throwable cause) {
		super(cause);
	}

	public BankException(String message, Throwable cause) {
		super(message, cause);
	}
}
