package com.capgemini.service;

import com.capgemini.dto.response.CommandResponse;
import com.capgemini.model.Account;
import com.capgemini.model.Customer;
import com.capgemini.repository.AccountRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static com.capgemini.common.Constants.*;

/**
 * Service used for Account related tasks
 */
@Slf4j(topic = "AccountService")
@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final CustomerService customerService;
    private final TransactionService transactionService;

    /**
     * Opens a new current account for the given customer
     *
     * @param customerId
     * @param initialCredit
     * @return
     */
    @Transactional
    public CommandResponse create(Long customerId, BigDecimal initialCredit) {
        // check if customer exists
        final Customer customer = customerService.getEntityById(customerId);
        final Account account = new Account();

        // if initialCredit is not 0, a transaction will be sent to the new account
        if (initialCredit.compareTo(BigDecimal.valueOf(0)) > 0) {
            account.addTransaction(transactionService.generateTransaction(customer, initialCredit));
            account.setBalance(initialCredit);
            log.info(TRANSACTION_ADDED);
        }
        account.setCustomer(customer);
        account.setBalance(initialCredit);
        accountRepository.save(account);
        return CommandResponse.builder().id(account.getId()).build();
    }
}
