package com.github.yildizmy.repository;

import com.github.yildizmy.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query(value = "SELECT t " +
            "FROM Transaction t " +
            "LEFT JOIN Account a ON t.account.id = a.id " +
            "WHERE a.customer.id = :customerId " +
            "ORDER BY t.date DESC")
    List<Transaction> findAllByCustomerId(@Param("customerId") Long customerId);
}
