package com.bank.retailbanking.service;

import java.util.Optional;

import com.bank.retailbanking.dto.FundTransferRequestDto;
import com.bank.retailbanking.dto.FundTransferResponseDto;
import com.bank.retailbanking.exception.AmountInvalidException;
import com.bank.retailbanking.exception.CreditAccountNotFoundException;
import com.bank.retailbanking.exception.DebitAccountNotFoundException;
import com.bank.retailbanking.exception.MinimumBalanceException;
import com.bank.retailbanking.exception.SameAccountNumberException;

public interface TransactionService {

	Optional<FundTransferResponseDto> fundTransfer(FundTransferRequestDto fundTransferRequestDto)
			throws DebitAccountNotFoundException, AmountInvalidException, MinimumBalanceException, CreditAccountNotFoundException, SameAccountNumberException;
}
