package com.bank.retailbanking.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MortgageAccountSummaryResponse {
	
	private Long accountNumber;
	private Double accountBalance;
	private String accountType;

}
