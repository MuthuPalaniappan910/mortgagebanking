package com.bank.retailbanking.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.bank.retailbanking.dto.CustomerFavouriteAccountResponse;
import com.bank.retailbanking.dto.FavouriteBeneficiariesResponseDto;
import com.bank.retailbanking.exception.CustomerAccountNotFoundException;
import com.bank.retailbanking.service.CustomerService;

import antlr.collections.List;
import lombok.extern.slf4j.Slf4j;

@RequestMapping("/customers")
@RestController
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
@Slf4j
public class CustomerController {
	@Autowired
	CustomerService customerService;

	@GetMapping("/{customerId}")
	public ResponseEntity<FavouriteBeneficiariesResponseDto> getBeneficiary(@PathVariable Long customerId) throws CustomerAccountNotFoundException {
		log.info("Getting beneficiary");
		
		final String uri = "http://10.117.189.62:8075/mybank/customers/" + customerId + "/beneficiary";

		RestTemplate restTemplate = new RestTemplate();
		// ArrayList result = restTemplate.getForObject(uri, ArrayList.class);

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

		FavouriteBeneficiariesResponseDto resBeneficiariesResponseDto = restTemplate
				.exchange(uri, HttpMethod.GET, entity, FavouriteBeneficiariesResponseDto.class).getBody();		
		if (resBeneficiariesResponseDto != null ) {			
			return new ResponseEntity<FavouriteBeneficiariesResponseDto>(resBeneficiariesResponseDto, HttpStatus.OK);
		}
		return new ResponseEntity<FavouriteBeneficiariesResponseDto>(HttpStatus.NO_CONTENT);
	}
}
