package com.bank.retailbanking.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDto {

	private Long customerId;
	private String password;
}
