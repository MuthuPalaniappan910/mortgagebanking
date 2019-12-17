package com.bank.retailbanking.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.retailbanking.constants.ApplicationConstants;
import com.bank.retailbanking.dto.FundTransferRequestDto;
import com.bank.retailbanking.dto.FundTransferResponseDto;
import com.bank.retailbanking.exception.AmountInvalidException;
import com.bank.retailbanking.exception.CreditAccountNotFoundException;
import com.bank.retailbanking.exception.DebitAccountNotFoundException;
import com.bank.retailbanking.exception.MinimumBalanceException;
import com.bank.retailbanking.exception.SameAccountNumberException;
import com.bank.retailbanking.service.TransactionService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
@RestController
@RequestMapping("/transactions")
@Slf4j
public class TransactionController {
	@Autowired
	TransactionService transactionService;

	/**
	 * @Description This method enables to transfer funds b/w two accounts
	 * 
	 * @param fundTransferRequestDto
	 * @return
	 * @throws MinimumBalanceException
	 * @throws AmountInvalidException
	 * @throws DebitAccountNotFoundException
	 * @throws CreditAccountNotFoundException 
	 * @throws SameAccountNumberException 
	 */

	@PostMapping
	public ResponseEntity<Optional<FundTransferResponseDto>> fundTransfer(
			@RequestBody FundTransferRequestDto fundTransferRequestDto)
			throws DebitAccountNotFoundException, AmountInvalidException, MinimumBalanceException, CreditAccountNotFoundException, SameAccountNumberException {
		Optional<FundTransferResponseDto> response = transactionService.fundTransfer(fundTransferRequestDto);
		if (response.isPresent()) {
			log.info("Transaction is successful");
			response.get().setStatusCode(ApplicationConstants.FUNDTRANSFER_SUCCESSCODE);
			response.get().setMessage(ApplicationConstants.FUNDTRANSFER_SUCCESSMESSAGE);
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		log.info("Transaction failed");
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}
}
