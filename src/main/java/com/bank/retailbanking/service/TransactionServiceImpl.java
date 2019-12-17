package com.bank.retailbanking.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.retailbanking.constants.ApplicationConstants;
import com.bank.retailbanking.dto.FundTransferRequestDto;
import com.bank.retailbanking.dto.FundTransferResponseDto;
import com.bank.retailbanking.entity.Customer;
import com.bank.retailbanking.entity.CustomerAccountDetail;
import com.bank.retailbanking.entity.CustomerTransaction;
import com.bank.retailbanking.exception.AmountInvalidException;
import com.bank.retailbanking.exception.CreditAccountNotFoundException;
import com.bank.retailbanking.exception.DebitAccountNotFoundException;
import com.bank.retailbanking.exception.MinimumBalanceException;
import com.bank.retailbanking.exception.SameAccountNumberException;
import com.bank.retailbanking.repository.CustomerAccountDetailRepository;
import com.bank.retailbanking.repository.CustomerRepository;
import com.bank.retailbanking.repository.CustomerTransactionRepository;

@Service
public class TransactionServiceImpl implements TransactionService {
	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	CustomerAccountDetailRepository customerAccountDetailRepository;

	@Autowired
	CustomerTransactionRepository customerTransactionRepository;

	@Override
	public Optional<FundTransferResponseDto> fundTransfer(FundTransferRequestDto fundTransferRequestDto)
			throws DebitAccountNotFoundException, AmountInvalidException, MinimumBalanceException,
			CreditAccountNotFoundException, SameAccountNumberException {
		FundTransferResponseDto fundTransferResponseDto = new FundTransferResponseDto();
		Optional<CustomerAccountDetail> customerAccountDetail = customerAccountDetailRepository
				.findByAccountNumberAndAccountType(fundTransferRequestDto.getDebitedAccount(),
						ApplicationConstants.SAVINGSACCOUNT_MESSAGE);
		if (customerAccountDetail.isPresent()) {
			Optional<Customer> customerDetail = customerRepository
					.findByCustomerId(customerAccountDetail.get().getCustomerId().getCustomerId());
			if (customerDetail.isPresent()) {
				Optional<CustomerAccountDetail> creditAccount = customerAccountDetailRepository
						.findByCustomerIdAndAccountTypeAndAccountNumber(customerDetail.get(),
								ApplicationConstants.MORTGAGE_MESSAGE, fundTransferRequestDto.getCreditedAccount());
				if (creditAccount.isPresent()) {
					Double availableBalance = customerAccountDetail.get().getAvailableBalance();
					Double transactionAmount = fundTransferRequestDto.getAmount();
					Double creditBeforeAmount = creditAccount.get().getAvailableBalance();
					if (transactionAmount < availableBalance) {
						Double afterTransactionBalance = availableBalance - transactionAmount;
						if (afterTransactionBalance > ApplicationConstants.MINIMUMBALANCE) {
							CustomerTransaction debitAction = new CustomerTransaction();
							debitAction.setAccountNumber(customerAccountDetail.get());
							Double debitBalance = availableBalance - transactionAmount;
							creditAccount.get().setAvailableBalance(debitBalance);
							debitAction.setTransactionAmount(transactionAmount);
							debitAction.setTransactionComments(fundTransferRequestDto.getRemarks());
							debitAction.setTransactionDate(LocalDate.now());
							debitAction.setTransactionStatus(ApplicationConstants.SUCCESS);
							debitAction.setTransactionType(ApplicationConstants.DEBIT);
							customerTransactionRepository.save(debitAction);
							customerAccountDetail.get().setAvailableBalance(afterTransactionBalance);
							customerAccountDetailRepository.save(customerAccountDetail.get());

							CustomerTransaction creditAction = new CustomerTransaction();
							creditAction.setAccountNumber(creditAccount.get());
							creditAction.setTransactionAmount(transactionAmount);
							creditAction.setTransactionComments(fundTransferRequestDto.getRemarks());
							creditAction.setTransactionDate(LocalDate.now());
							creditAction.setTransactionStatus(ApplicationConstants.SUCCESS);
							creditAction.setTransactionType(ApplicationConstants.CREDIT);
							customerTransactionRepository.save(creditAction);
							Double creditBalance = creditBeforeAmount + transactionAmount;
							creditAccount.get().setAvailableBalance(creditBalance);
							customerAccountDetailRepository.save(creditAccount.get());
							return (Optional.of(fundTransferResponseDto));
						}
						throw new MinimumBalanceException(ApplicationConstants.AMOUNT_MINIMUMBALANCE);
					}
					throw new AmountInvalidException(ApplicationConstants.AMOUNT_AVAILABLEBALANCE);
				}
				Optional<CustomerAccountDetail> creditedAccount = customerAccountDetailRepository
						.findByAccountTypeAndAccountNumber(ApplicationConstants.SAVINGSACCOUNT_MESSAGE,
								fundTransferRequestDto.getCreditedAccount());
				if (creditedAccount.isPresent()) {
					if (customerAccountDetail.get().getAccountNumber() != creditedAccount.get().getAccountNumber()) {
						Double availableBalance = customerAccountDetail.get().getAvailableBalance();
						Double transactionAmount = fundTransferRequestDto.getAmount();
						Double creditBeforeAmount = creditedAccount.get().getAvailableBalance();
						if (transactionAmount < availableBalance) {
							Double afterTransactionBalance = availableBalance - transactionAmount;
							if (afterTransactionBalance > ApplicationConstants.MINIMUMBALANCE) {
								CustomerTransaction debitAction = new CustomerTransaction();
								debitAction.setAccountNumber(customerAccountDetail.get());
								debitAction.setTransactionAmount(transactionAmount);
								debitAction.setTransactionComments(fundTransferRequestDto.getRemarks());
								debitAction.setTransactionDate(LocalDate.now());
								debitAction.setTransactionStatus(ApplicationConstants.SUCCESS);
								debitAction.setTransactionType(ApplicationConstants.DEBIT);
								customerTransactionRepository.save(debitAction);
								customerAccountDetail.get().setAvailableBalance(afterTransactionBalance);
								customerAccountDetailRepository.save(customerAccountDetail.get());

								CustomerTransaction creditAction = new CustomerTransaction();
								creditAction.setAccountNumber(creditedAccount.get());
								creditAction.setTransactionAmount(transactionAmount);
								creditAction.setTransactionComments(fundTransferRequestDto.getRemarks());
								creditAction.setTransactionDate(LocalDate.now());
								creditAction.setTransactionStatus(ApplicationConstants.SUCCESS);
								creditAction.setTransactionType(ApplicationConstants.CREDIT);
								customerTransactionRepository.save(creditAction);
								Double creditBalance = creditBeforeAmount + transactionAmount;
								creditedAccount.get().setAvailableBalance(creditBalance);
								customerAccountDetailRepository.save(creditedAccount.get());
								return (Optional.of(fundTransferResponseDto));
							}
							throw new MinimumBalanceException(ApplicationConstants.AMOUNT_MINIMUMBALANCE);
						}
						throw new AmountInvalidException(ApplicationConstants.AMOUNT_AVAILABLEBALANCE);
					}
					throw new SameAccountNumberException(ApplicationConstants.SAMEACCOUNT_MESSAGE);
				}
				throw new CreditAccountNotFoundException(ApplicationConstants.CREDITACCOUNT_NOTFOUND);
			}
			throw new DebitAccountNotFoundException(ApplicationConstants.DEBITACCOUNT_NOTFOUND);
		}
		throw new DebitAccountNotFoundException(ApplicationConstants.DEBITACCOUNT_NOTFOUND);
	}
}
