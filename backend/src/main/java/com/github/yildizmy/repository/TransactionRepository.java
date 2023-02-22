package com.github.yildizmy.repository;

import com.github.yildizmy.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findAllByAccountCustomerId(Long customerId);
}
