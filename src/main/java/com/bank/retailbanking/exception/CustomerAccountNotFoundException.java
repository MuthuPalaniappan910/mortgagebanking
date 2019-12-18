package com.bank.retailbanking.exception;

public class CustomerAccountNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;

	public CustomerAccountNotFoundException(String exception) {
		super(exception);
	}
}