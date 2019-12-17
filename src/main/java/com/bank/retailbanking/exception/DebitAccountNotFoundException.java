package com.bank.retailbanking.exception;

public class DebitAccountNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;

	public DebitAccountNotFoundException(String exception) {
		super(exception);
	}
}