package com.bank.retailbanking.constants;

public class ApplicationConstants {
	private ApplicationConstants() {

	}

	public static final String ACCOUNT_LIST_SUCCESS_MESSAGE = "Account lists are displayed";
	public static final Integer LIST_FAILURE_CODE = 404;

	public static final Integer SUCCESS_CODE = 200;

	public static final String ACCOUNT_LIST_FAILURE_MESSAGE = "There are no account details as you entered";

	public static final String SAVING_ACCOUNT = "savings";

	public static Integer FUNDTRANSFER_SUCCESSCODE = 200;
	public static String FUNDTRANSFER_SUCCESSMESSAGE = "Your fund is transferred successfully";
	public static String FUNDTRANSFER_FAILUREMESSAGE = "Transaction failed";
	public static String SAVINGSACCOUNT_MESSAGE = "Savings";

	public static String DEBITACCOUNT_NOTFOUND = "There is no debit account found";
	public static String CREDITACCOUNT_NOTFOUND = "There is no credit account found";
	public static String MORTGAGE_MESSAGE = "Mortgage";

	public static Double MINIMUMBALANCE = 2000.00;
	public static String AMOUNT_AVAILABLEBALANCE = "Sorry!!!Your transaction amount is more than your available balance";

	public static String SAMEACCOUNT_MESSAGE = "You will not be able to transfer to your account itself";

	public static String AMOUNT_MINIMUMBALANCE = "You should maintain a minimum balance of Rs.3000. Your balance will be insufficicent after this transaction";
	public static String SUCCESS = "Success";
	public static String DEBIT = "Debit";
	public static String CREDIT = "Credit";
}
