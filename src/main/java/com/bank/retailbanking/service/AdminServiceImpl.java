package com.bank.retailbanking.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.retailbanking.constants.ApplicationConstants;
import com.bank.retailbanking.dto.AccountList;
import com.bank.retailbanking.dto.ViewAccountResponseDto;
import com.bank.retailbanking.entity.Customer;
import com.bank.retailbanking.entity.CustomerAccountDetail;
import com.bank.retailbanking.exception.NoAccountListException;
import com.bank.retailbanking.repository.CustomerAccountDetailRepository;
import com.bank.retailbanking.repository.CustomerRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	CustomerAccountDetailRepository customerAccountDetailRepository;

	@Autowired
	CustomerRepository customerRepository;

	/**
	 * @author Muthu This method is used to get partial accountnuber
	 * @param accountNumber
	 * @return
	 * @throws NoAccountListException
	 */
	public List<AccountList> getAccountList(String accountNumber) throws NoAccountListException {
		List<CustomerAccountDetail> customerAccountDetail = customerAccountDetailRepository
				.findAllByAccountNumber(accountNumber);
		if (customerAccountDetail.isEmpty()) {
			throw new NoAccountListException(ApplicationConstants.ACCOUNT_LIST_FAILURE_MESSAGE);
		}
		customerAccountDetail = customerAccountDetail.stream().filter(
				accountType -> (accountType.getAccountType().equalsIgnoreCase(ApplicationConstants.SAVING_ACCOUNT)))
				.collect(Collectors.toList());
		List<AccountList> accounts = new ArrayList<>();
		customerAccountDetail.forEach(customerAccountDetails -> {
			AccountList accountList = new AccountList();
			BeanUtils.copyProperties(customerAccountDetails, accountList);
			accountList.setCustomerId(customerAccountDetails.getCustomerId().getCustomerId());
			accountList.setAccountName(customerAccountDetails.getCustomerId().getFirstName().concat(" ")
					.concat((customerAccountDetails.getCustomerId().getLastName())));
			accounts.add(accountList);

		});
		return accounts;
	}

	/**
	 * @author Bindhu 
	 * This method is used to get the account details mortgage and
	 *         savings
	 * @param accountNumber
	 * @return
	 */
	@Override
	public ViewAccountResponseDto viewAccountDetails(Long accountNumber) {
		log.info("Entering into viewAccountDetails method of service");
		Optional<CustomerAccountDetail> customerAccountDetail = customerAccountDetailRepository
				.findByAccountNumber(accountNumber);
		ViewAccountResponseDto viewAccountResponseDto = new ViewAccountResponseDto();
		if (customerAccountDetail.isPresent()) {
			Customer customer = customerAccountDetail.get().getCustomerId();
			Optional<Customer> customerDetails = customerRepository.findByCustomerId(customer.getCustomerId());
			if (customerDetails.isPresent()) {
				viewAccountResponseDto.setAccountNumber(accountNumber);
				viewAccountResponseDto.setAccountType(customerAccountDetail.get().getAccountType());
				viewAccountResponseDto.setCustomerId(customerDetails.get().getCustomerId());
				viewAccountResponseDto.setCustomerName(
						customerDetails.get().getFirstName() + " " + customerDetails.get().getLastName());
				viewAccountResponseDto
						.setAge(LocalDate.now().getYear() - customerDetails.get().getDateOfBirth().getYear());
				viewAccountResponseDto.setGender(customerDetails.get().getGender());
			}
		}
		return viewAccountResponseDto;
	}

}
