package com.bank.retailbanking.service;

import com.bank.retailbanking.dto.CustomerFavouriteAccountResponse;
import com.bank.retailbanking.exception.CustomerAccountNotFoundException;

public interface CustomerService {

	CustomerFavouriteAccountResponse getMortgageDetails(Long customerId) throws CustomerAccountNotFoundException;

}
