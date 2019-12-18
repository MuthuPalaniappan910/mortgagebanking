package com.bank.retailbanking.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.retailbanking.constants.ApplicationConstants;
import com.bank.retailbanking.dto.LoginRequestDto;
import com.bank.retailbanking.dto.LoginResponseDto;
import com.bank.retailbanking.entity.Customer;
import com.bank.retailbanking.exception.GeneralException;
import com.bank.retailbanking.repository.CustomerRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class LoginServiceImpl implements LoginService {

	@Autowired
	CustomerRepository customerRepository;

	/**
	 * @author Chethana
	 * @Description This method is used for user to login with valid credentials
	 * @param loginRequestdto
	 * @return LoginResponsedto
	 * @exception LOGIN_ERROR
	 */
	public Optional<LoginResponseDto> login(LoginRequestDto loginRequestdto) throws GeneralException {
		log.info("Entering into login() method of LoginServiceImpl");
		Optional<Customer> customerResponse = customerRepository
				.findByCustomerIdAndPassword(loginRequestdto.getCustomerId(), loginRequestdto.getPassword());
		if (!customerResponse.isPresent()) {
			log.error("Exception occured in login() method of LoginServiceImpl");
			throw new GeneralException(ApplicationConstants.LOGIN_ERROR);
		}
		LoginResponseDto loginResponsedto = new LoginResponseDto();
		loginResponsedto.setCustomerID(customerResponse.get().getCustomerId());
		loginResponsedto.setCustomerName(customerResponse.get().getFirstName().concat(" ").concat(customerResponse.get().getLastName()));
		loginResponsedto.setRoleType(customerResponse.get().getRoleType());
		return Optional.of(loginResponsedto);
	}
}
