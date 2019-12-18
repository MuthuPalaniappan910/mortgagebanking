package com.bank.retailbanking.service;

import java.util.Optional;

import com.bank.retailbanking.dto.LoginRequestDto;
import com.bank.retailbanking.dto.LoginResponseDto;
import com.bank.retailbanking.exception.GeneralException;




public interface LoginService {
	public Optional<LoginResponseDto> login(LoginRequestDto loginRequestdto) throws GeneralException;

}
