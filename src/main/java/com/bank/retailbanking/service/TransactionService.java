package com.bank.retailbanking.service;

import com.bank.retailbanking.dto.MortgageAccountSummaryResponsedto;
import com.bank.retailbanking.exception.GeneralException;

public interface TransactionService {

	MortgageAccountSummaryResponsedto getAccountSummary(Long customerId) throws GeneralException;

}
