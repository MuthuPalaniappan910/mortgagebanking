package com.bank.retailbanking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.retailbanking.constants.ApplicationConstants;
import com.bank.retailbanking.dto.AccountList;
import com.bank.retailbanking.dto.AccountSearchResponseDto;
import com.bank.retailbanking.dto.ViewAccountResponseDto;
import com.bank.retailbanking.exception.NoAccountListException;
import com.bank.retailbanking.service.AdminService;

import lombok.extern.slf4j.Slf4j;

/**
 * This class is used to perform the operations related to admin
 * 
 **/
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
@RestController
@RequestMapping("/admin/customers")
@Slf4j
public class AdminController {

	@Autowired
	AdminService adminService;

	/**
	 * @author Muthu This method is used to get partial accountnuber
	 * @param accountNumber
	 * @return AccountSearchResponseDto
	 * @throws NoAccountListException
	 */

	@GetMapping("/{accountNumber}")
	public ResponseEntity<AccountSearchResponseDto> getAccountList(@PathVariable("accountNumber") String accountNumber)
			throws NoAccountListException {
		log.info("Searching list by partial account number");
		AccountSearchResponseDto accountSearchResponseDto = new AccountSearchResponseDto();
		List<AccountList> accountList = adminService.getAccountList(accountNumber);
		if (accountList.isEmpty()) {
			accountSearchResponseDto.setStatusCode(ApplicationConstants.LIST_FAILURE_CODE);
			accountSearchResponseDto.setMessage(ApplicationConstants.ACCOUNT_LIST_FAILURE_MESSAGE);
			return new ResponseEntity<>(accountSearchResponseDto, HttpStatus.NOT_FOUND);
		}
		accountSearchResponseDto.setAccountList(accountList);
		accountSearchResponseDto.setStatusCode(ApplicationConstants.SUCCESS_CODE);
		accountSearchResponseDto.setMessage(ApplicationConstants.ACCOUNT_LIST_SUCCESS_MESSAGE);
		return new ResponseEntity<>(accountSearchResponseDto, HttpStatus.OK);
	}

	/**
	 * @author Bindhu This method is used to get the account details mortgage and
	 *         savings
	 * @param accountNumber
	 * @return ViewAccountResponseDto
	 */
	@GetMapping("/customer/{accountNumber}")
	public ResponseEntity<ViewAccountResponseDto> viewAccountDetails(@PathVariable Long accountNumber) {
		log.info("Entering into viewAccountDetails method of controller");
		ViewAccountResponseDto viewAccountResponseDto = adminService.viewAccountDetails(accountNumber);
		viewAccountResponseDto.setStatusCode(ApplicationConstants.SUCCESS_CODE);
		viewAccountResponseDto.setMessage(ApplicationConstants.SUCCESS);
		return new ResponseEntity<>(viewAccountResponseDto, HttpStatus.OK);

	}

}
