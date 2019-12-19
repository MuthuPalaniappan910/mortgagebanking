package com.bank.retailbanking.dto;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionResponsedto {
	private String message;
	private Integer statusCode;
	private List<TransactionResponse> transactions;
}
