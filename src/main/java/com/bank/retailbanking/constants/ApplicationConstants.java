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
	public static final String BENEFICIARY_ADDED_SUCCESSFULLY = "Beneficiary details saved successfully";
	private ApplicationConstants() {

	}

	public static final String ACCOUNT_LIST_SUCCESS_MESSAGE = "Account lists are displayed";
	public static final Integer LIST_FAILURE_CODE = 404;

	public static final Integer SUCCESS_CODE = 200;
	public static final String LOGIN_ERROR = "Invalid customer credentials, Please try with valid Customer Id and Password";
	public static final String LOGIN_SUCCESS = "Login Success";

	public static final String ACCOUNT_LIST_FAILURE_MESSAGE = "No matching accounts found";

	public static final String SAVING_ACCOUNT = "Savings";
	public static final String MORTGAGE_ACCOUNT="Mortgage";
	public static final Long TRANSACTION_HISTORY_COUNT = 5L;

	public static Integer FUNDTRANSFER_SUCCESSCODE = 200;
	public static String FUNDTRANSFER_SUCCESSMESSAGE = "Your fund is transferred successfully";
	public static String FUNDTRANSFER_FAILUREMESSAGE = "Transaction failed";
	public static String SAVINGSACCOUNT_MESSAGE = "Savings";

	public static String DEBITACCOUNT_NOTFOUND = "There is no debit account found";
	public static String CREDITACCOUNT_NOTFOUND = "There is no credit account found";

	public static Double MINIMUMBALANCE = 3000.00;
	public static String AMOUNT_AVAILABLEBALANCE = "Sorry!!!Your transaction amount is more than your available balance";

	public static String SAMEACCOUNT_MESSAGE = "You will not be able to transfer to your account itself";

	public static String AMOUNT_MINIMUMBALANCE = "You should maintain a minimum balance of Rs.3000. Your balance will be insufficicent after this transaction";
	public static String SUCCESS = "Success";
	public static String DEBIT = "Debit";
	public static String CREDIT = "Credit";
	
	public static String SUCCESSFULLY_DEBITED = "Successfully received from ";
	public static String SUCCESSFULLY_CREDITED = "Successfully credited to ";
}
