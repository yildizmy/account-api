package com.github.yildizmy.service;

import com.github.yildizmy.dto.response.TransactionResponse;
import com.github.yildizmy.exception.NoSuchElementFoundException;
import com.github.yildizmy.model.Customer;
import com.github.yildizmy.model.Transaction;
import com.github.yildizmy.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static com.github.yildizmy.common.Constants.NOT_FOUND_RECORD;
import static com.github.yildizmy.common.Constants.TRANSACTION_DESCRIPTION;

/**
 * Service used for Transaction related tasks
 */
@Slf4j(topic = "TransactionService")
@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;

    /**
     * Generates a new transaction for the given customer with the initial credit
     *
     * @param customer
     * @param initialCredit
     * @return
     */
    public Transaction generateTransaction(Customer customer, BigDecimal initialCredit) {
        final Transaction transaction = new Transaction();
        transaction.setAmount(initialCredit);
        transaction.setDescription(String.format(TRANSACTION_DESCRIPTION, customer.getName(), customer.getSurname(), initialCredit));
        transaction.setDate(LocalDateTime.now());
        return transaction;
    }

    /**
     * Fetches all transactions by the given customer
     *
     * @param customerId
     * @return
     */
    @Transactional(readOnly = true)
    public List<TransactionResponse> findAllByCustomerId(Long customerId) {
        final List<TransactionResponse> transactions = transactionRepository.findAllByCustomerId(customerId).stream()
                .map(TransactionResponse::new).toList();

        if (transactions.isEmpty()) {
            log.error(NOT_FOUND_RECORD);
            throw new NoSuchElementFoundException(NOT_FOUND_RECORD);
        }
        return transactions;
    }
}
