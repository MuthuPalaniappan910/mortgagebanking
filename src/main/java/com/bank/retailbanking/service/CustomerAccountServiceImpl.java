package com.bank.retailbanking.service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.bank.retailbanking.constants.ApplicationConstants;
import com.bank.retailbanking.dto.CustomerRequestDto;
import com.bank.retailbanking.dto.CustomerResponseDto;
import com.bank.retailbanking.entity.Customer;
import com.bank.retailbanking.entity.CustomerAccountDetail;
import com.bank.retailbanking.exception.GeneralException;
import com.bank.retailbanking.repository.CustomerAccountDetailRepository;
import com.bank.retailbanking.repository.CustomerRepository;

@Service
public class CustomerAccountServiceImpl implements CustomerAccountService {
	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	CustomerAccountDetailRepository customerAccountDetailsRepository;
	@Autowired
	private JavaMailSender javaMailSender;
	Random random = new Random();
	/**
	 * @author Mahesh
	 * This method is used to create a mortgage account for a particular savings account
	 * @param customerRequestDto
	 * @return CustomerResponseDto
	 * @throws GeneralException
	 */
	@Override
	public Optional<CustomerResponseDto> createCustomerAccount(CustomerRequestDto customerRequestDto)
			throws GeneralException {

		Optional<Customer> customer = customerRepository.findByCustomerId(customerRequestDto.getCustomerId());

		if (customer.isPresent()) {

			Optional<CustomerAccountDetail> customerAccoundDetailsResponse = customerAccountDetailsRepository
					.findByCustomerIdAndAccountType(customer, "Mortgage");

			if (customerAccoundDetailsResponse.isPresent()) {
				throw new GeneralException("Mortgage account already created");
			}

			CustomerAccountDetail customerAccountDetail = new CustomerAccountDetail();
			customerAccountDetail.setAccountOpeningDate(LocalDate.now());
			customerAccountDetail.setAccountType("Mortgage");
			customerAccountDetail.setAvailableBalance(0-customerRequestDto.getLoanAmount());
			customerAccountDetail.setCustomerId(customer.get());
			String password = customer.get().getFirstName().substring(0) + random.nextInt(9999);
			BeanUtils.copyProperties(customerRequestDto, customerAccountDetail);
			customer.get().setPassword(password);
			customerAccountDetail = customerAccountDetailsRepository.save(customerAccountDetail);

			SimpleMailMessage simple = new SimpleMailMessage();
			simple.setTo(customer.get().getCustomerEmail());
			simple.setFrom(ApplicationConstants.MORTGAGE_FROM_GMAILID);
			simple.setSubject(ApplicationConstants.MORTGAGE_GMAIL_SUBJECT);

			simple.setText(ApplicationConstants.MORTGAGE_TEXT_ONE + ApplicationConstants.NEXT_LINE
					+ ApplicationConstants.MORTGAGE_TEXT_TWO + ApplicationConstants.WHITE_SPACE + "-------------------"
					+ ApplicationConstants.NEXT_LINE + ApplicationConstants.MORTGAGE_TEXT_THREE
					+ customerAccountDetail.getCustomerId().getCustomerId() + ApplicationConstants.NEXT_LINE
					+ ApplicationConstants.MORTGAGE_TEXT_FOUR + ApplicationConstants.WHITE_SPACE + password
					+ ApplicationConstants.WHITE_SPACE + ApplicationConstants.NEXT_LINE
					+ ApplicationConstants.MORTGAGE_TEXT_FIVE);
			javaMailSender.send(simple);

		} else {
			throw new GeneralException(ApplicationConstants.CUSTOMER_NOT_FOUND_MESSAGE);
		}
		CustomerResponseDto customerResponseDto = new CustomerResponseDto();
		return Optional.of(customerResponseDto);

	}

}
