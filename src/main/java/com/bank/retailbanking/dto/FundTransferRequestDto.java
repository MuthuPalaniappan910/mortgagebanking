package com.bank.retailbanking.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FundTransferRequestDto {
	private Long debitedAccount;
	private Long creditedAccount;
	private Double amount;
	private String remarks;
}
