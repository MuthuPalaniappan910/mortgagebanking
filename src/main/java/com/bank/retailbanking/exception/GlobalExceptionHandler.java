package com.bank.retailbanking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {
	@ExceptionHandler(value = DebitAccountNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleException(DebitAccountNotFoundException exception) {
		ErrorResponse errorResponse = new ErrorResponse(exception.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = SameAccountNumberException.class)
	public ResponseEntity<ErrorResponse> handleException(SameAccountNumberException exception) {
		ErrorResponse errorResponse = new ErrorResponse(exception.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = CreditAccountNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleException(CreditAccountNotFoundException exception) {
		ErrorResponse errorResponse = new ErrorResponse(exception.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = MinimumBalanceException.class)
	public ResponseEntity<ErrorResponse> handleException(MinimumBalanceException exception) {
		ErrorResponse errorResponse = new ErrorResponse(exception.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = AmountInvalidException.class)
	public ResponseEntity<ErrorResponse> handleException(AmountInvalidException exception) {
		ErrorResponse errorResponse = new ErrorResponse(exception.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = GeneralException.class)
	public ResponseEntity<ErrorResponse> handleException(GeneralException exception) {
		ErrorResponse errorResponse = new ErrorResponse(exception.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = NoAccountListException.class)
	public ResponseEntity<ErrorResponse> handleException(NoAccountListException exception) {
		ErrorResponse errorResponse = new ErrorResponse(exception.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}

}