package com.bank.retailbanking.constants;

import org.springframework.http.HttpStatus;

public class ApplicationConstants {
	
	public static final Integer SUCESS_STATUS_CODE = HttpStatus.OK.value();

	public static final String MORTGAGE_MESSAGE="You can transfer only to your mortgage account";
	public static final String CUSTOMER_NOT_FOUND_MESSAGE = "Please check your id entered";
	public static final String MORTGAGE_FROM_GMAILID = "testmail2521@gmail.com";
	public static final String MORTGAGE_GMAIL_SUBJECT = "MORTGAGE";
	public static final String MORTGAGE_TEXT_ONE = "WELCOME TO MORTGAGE";
	public static final String MORTGAGE_TEXT_TWO = "HI";
	public static final String MORTGAGE_TEXT_THREE = "your MORTGAGE customerId is";
	public static final String MORTGAGE_TEXT_FOUR = "your MORTGAGE password is";
	public static final String MORTGAGE_TEXT_FIVE = "NOTE :- DONT SHARE YOUR CREDENTIALS";
	public static final String NEXT_LINE = "\n";
	public static final String WHITE_SPACE = "\t";
	public static final String MORTGAGE_SUCCESS_STATUS_MESSAGE = "login successful your login credentials are sent to your gmail";
	public static final String MORTGAGE_FAILURE_STATUS_MESSAGE = "please enter current details";
	public static final Integer MORTGAGE_FAILURE_STATUS_CODE = 404;
}
