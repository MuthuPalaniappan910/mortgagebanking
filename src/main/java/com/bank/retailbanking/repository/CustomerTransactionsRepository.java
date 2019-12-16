package com.bank.retailbanking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.retailbanking.entity.CustomerTransaction;

@Repository
public interface CustomerTransactionsRepository extends JpaRepository<CustomerTransaction, Long> {

}
