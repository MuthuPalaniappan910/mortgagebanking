package com.bank.retailbanking.exception;

public class MinimumBalanceException extends Exception {
	private static final long serialVersionUID = 1L;

	public MinimumBalanceException(String exception) {
		super(exception);
	}
}