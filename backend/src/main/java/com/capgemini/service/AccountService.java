package com.capgemini.service;

import com.capgemini.dto.response.AccountResponse;
import com.capgemini.dto.response.CommandResponse;
import com.capgemini.exception.NoSuchElementFoundException;
import com.capgemini.model.Account;
import com.capgemini.model.Customer;
import com.capgemini.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static com.capgemini.common.Constants.NOT_FOUND_RECORD;
import static com.capgemini.common.Constants.TRANSACTION_ADDED;

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
     * Fetches all accounts
     *
     * @return account list
     */
    @Transactional(readOnly = true)
    public List<AccountResponse> findAll() {
        final List<AccountResponse> accounts = accountRepository.findAll().stream()
                .map(AccountResponse::new)
                .toList();

        if (accounts.isEmpty()) {
            log.error(NOT_FOUND_RECORD);
            throw new NoSuchElementFoundException(NOT_FOUND_RECORD);
        }
        return accounts;
    }

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
