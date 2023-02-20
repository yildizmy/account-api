package com.capgemini.service;

import com.capgemini.model.Customer;
import com.capgemini.model.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.capgemini.common.Constants.TRANSACTION_DESCRIPTION;

/**
 * Service used for Transaction related tasks
 */
@Service
@RequiredArgsConstructor
public class TransactionService {

    public Transaction generateTransaction(Customer customer, BigDecimal initialCredit) {
        final Transaction transaction = new Transaction();
        transaction.setDescription(String.format(TRANSACTION_DESCRIPTION, customer.getName(), customer.getSurname(), initialCredit));
        transaction.setDate(LocalDateTime.now());
        return transaction;
    }
}
