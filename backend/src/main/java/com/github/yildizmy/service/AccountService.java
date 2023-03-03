package com.github.yildizmy.service;

import com.github.yildizmy.dto.response.AccountResponse;
import com.github.yildizmy.dto.response.CommandResponse;
import com.github.yildizmy.exception.NoSuchElementFoundException;
import com.github.yildizmy.model.Account;
import com.github.yildizmy.model.Customer;
import com.github.yildizmy.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static com.github.yildizmy.common.Constants.*;

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

        if (accounts.isEmpty())
            throw new NoSuchElementFoundException(NOT_FOUND_RECORD);
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
        log.info(ACCOUNT_CREATED);
        return CommandResponse.builder().id(account.getId()).build();
    }
}
