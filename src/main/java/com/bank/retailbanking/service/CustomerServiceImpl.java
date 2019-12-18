package com.bank.retailbanking.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.retailbanking.constants.ApplicationConstants;
import com.bank.retailbanking.dto.CustomerFavouriteAccountResponse;
import com.bank.retailbanking.entity.Customer;
import com.bank.retailbanking.entity.CustomerAccountDetail;
import com.bank.retailbanking.exception.CustomerAccountNotFoundException;
import com.bank.retailbanking.repository.CustomerAccountDetailRepository;
import com.bank.retailbanking.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	CustomerAccountDetailRepository customerAccountDetailRepository;

	@Autowired
	CustomerRepository customerRepository;

	@Override
	public CustomerFavouriteAccountResponse getMortgageDetails(Long customerId)
			throws CustomerAccountNotFoundException {
		CustomerFavouriteAccountResponse customerFavouriteAccountResponse = new CustomerFavouriteAccountResponse();
		Optional<Customer> customer = customerRepository.findByCustomerId(customerId);
		if (customer.isPresent()) {
			Optional<CustomerAccountDetail> mortgageAccount = customerAccountDetailRepository
					.findByCustomerIdAndAccountType(customer, ApplicationConstants.MORTGAGE_MESSAGE);
			if (mortgageAccount.isPresent()) {
				customerFavouriteAccountResponse.setBeneficiaryAccountName(customer.get().getFirstName());
				customerFavouriteAccountResponse.setBeneficiaryAccountNumber(mortgageAccount.get().getAccountNumber());
			}
		}
		return customerFavouriteAccountResponse;
	}
}
