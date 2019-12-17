package com.bank.retailbanking.exception;

public class CreditAccountNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;

	public CreditAccountNotFoundException(String exception) {
		super(exception);
	}
}