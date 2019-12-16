package com.bank.retailbanking.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.retailbanking.dto.MortgageAccountSummaryResponse;
import com.bank.retailbanking.dto.MortgageAccountSummaryResponsedto;
import com.bank.retailbanking.entity.Customer;
import com.bank.retailbanking.entity.CustomerAccountDetail;
import com.bank.retailbanking.exception.GeneralException;
import com.bank.retailbanking.repository.CustomerAccountDetailRepository;
import com.bank.retailbanking.repository.CustomerRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	CustomerAccountDetailRepository customerAccountDetailRepository;

	@Override
	public MortgageAccountSummaryResponsedto getAccountSummary(Long customerId) throws GeneralException {
		log.info("Entering into AccountSummaryServiceImplementation--------getAccountSummary() Method");
		Optional<Customer> customerDetails = customerRepository.findById(customerId);
		MortgageAccountSummaryResponsedto mortgageAccountSummaryResponsedto = new MortgageAccountSummaryResponsedto();
		if (!customerDetails.isPresent()) {
			throw new GeneralException("Invalid customer");
		}
		List<CustomerAccountDetail> customerAccountDetails = customerAccountDetailRepository
				.findAllByCustomerId(customerDetails.get());

		List<MortgageAccountSummaryResponse> mortgageAccountSummaryResponses = new ArrayList<>();

		for (CustomerAccountDetail customerAccountDetail : customerAccountDetails) {
			MortgageAccountSummaryResponse mortgageAccountSummaryResponse = new MortgageAccountSummaryResponse();
			mortgageAccountSummaryResponse.setAccountBalance(customerAccountDetail.getAvailableBalance());
			mortgageAccountSummaryResponse.setAccountNumber(customerAccountDetail.getAccountNumber());
			mortgageAccountSummaryResponse.setAccountType(customerAccountDetail.getAccountType());
			mortgageAccountSummaryResponses.add(mortgageAccountSummaryResponse);
		}
		mortgageAccountSummaryResponsedto.setAccountDetails(mortgageAccountSummaryResponses);
		return mortgageAccountSummaryResponsedto;
	}

}
