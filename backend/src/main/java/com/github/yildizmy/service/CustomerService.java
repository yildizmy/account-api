package com.github.yildizmy.service;

import com.github.yildizmy.dto.mapper.CustomerRequestMapper;
import com.github.yildizmy.dto.request.CustomerRequest;
import com.github.yildizmy.dto.response.CommandResponse;
import com.github.yildizmy.dto.response.CustomerResponse;
import com.github.yildizmy.dto.response.TransactionResponse;
import com.github.yildizmy.exception.ElementAlreadyExistsException;
import com.github.yildizmy.exception.NoSuchElementFoundException;
import com.github.yildizmy.model.Account;
import com.github.yildizmy.model.Customer;
import com.github.yildizmy.repository.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import static com.github.yildizmy.common.Constants.*;

/**
 * Service used for Customer related tasks
 */
@Slf4j(topic = "CustomerService")
@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerRequestMapper customerRequestMapper;

    /**
     * Fetches a customer by the given id
     *
     * @param id
     * @return
     */
    public Customer getEntityById(Long id) {
        return customerRepository.findById(id).orElseThrow(() -> {
            log.error(NOT_FOUND_CUSTOMER);
            return new EntityNotFoundException(NOT_FOUND_CUSTOMER);
        });
    }

    /**
     * Fetches a customer by the given id
     *
     * @param id
     * @return
     */
    public CustomerResponse findById(Long id) {
        return customerRepository.findById(id).map(CustomerResponse::new).orElseThrow(() -> {
            log.error(NOT_FOUND_CUSTOMER);
            return new NoSuchElementFoundException(NOT_FOUND_CUSTOMER);
        });
    }

    /**
     * Fetches all customers with account and transaction data
     *
     * @return customer list
     */
    @Transactional(readOnly = true)
    public List<CustomerResponse> findAll() {
        final List<CustomerResponse> customers = customerRepository.findAll().stream()
                .map(customer -> new CustomerResponse(
                        customer,
                        customer.getAccounts().stream().map(Account::getBalance).reduce(BigDecimal.ZERO, BigDecimal::add),
                        customer.getAccounts().stream().flatMap(account -> account.getTransactions().stream()
                                .map(TransactionResponse::new)).collect(Collectors.toSet())))
                .toList();

        if (customers.isEmpty()) {
            log.error(NOT_FOUND_RECORD);
            throw new NoSuchElementFoundException(NOT_FOUND_RECORD);
        }
        return customers;
    }

    /**
     * Creates a new customer using the given request parameters
     *
     * @param request
     * @return id of created customer
     */
    public CommandResponse create(CustomerRequest request) {
        if (customerRepository.existsByEmailIgnoreCase(request.getEmail())) {
            log.error(ALREADY_EXISTS_CUSTOMER);
            throw new ElementAlreadyExistsException(ALREADY_EXISTS_CUSTOMER);
        }

        final Customer customer = customerRequestMapper.toEntity(request);
        customerRepository.save(customer);
        return CommandResponse.builder().id(customer.getId()).build();
    }
}
