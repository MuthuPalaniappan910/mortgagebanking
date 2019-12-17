package com.bank.retailbanking.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.retailbanking.entity.Customer;
import com.bank.retailbanking.entity.CustomerAccountDetail;

@Repository
public interface CustomerAccountDetailRepository extends JpaRepository<CustomerAccountDetail, Long> {

	Optional<CustomerAccountDetail> findByAccountNumberAndAccountType(Long debitedAccount,
			String SAVINGSACCOUNT_MESSAGE);

	Optional<CustomerAccountDetail> findByCustomerIdAndAccountTypeAndAccountNumber(Customer customer,
			String mORTGAGE_MESSAGE, Long creditedAccount);

	Optional<CustomerAccountDetail> findByAccountTypeAndAccountNumber(String SAVINGSACCOUNT_MESSAGE,
			Long creditedAccount);

}
