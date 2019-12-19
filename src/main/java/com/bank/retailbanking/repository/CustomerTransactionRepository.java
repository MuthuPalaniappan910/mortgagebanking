package com.bank.retailbanking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.retailbanking.entity.CustomerAccountDetail;
import com.bank.retailbanking.entity.CustomerTransaction;

@Repository
public interface CustomerTransactionRepository extends JpaRepository<CustomerTransaction, Long> {

	List<CustomerTransaction> findByAccountNumber(CustomerAccountDetail customerAccountDetail);

	
}
