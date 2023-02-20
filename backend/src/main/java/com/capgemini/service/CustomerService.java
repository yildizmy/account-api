package com.capgemini.service;

import com.capgemini.dto.mapper.CustomerRequestMapper;
import com.capgemini.dto.request.CustomerRequest;
import com.capgemini.dto.response.CommandResponse;
import com.capgemini.dto.response.CustomerResponse;
import com.capgemini.dto.response.TransactionResponse;
import com.capgemini.exception.ElementAlreadyExistsException;
import com.capgemini.exception.NoSuchElementFoundException;
import com.capgemini.model.Account;
import com.capgemini.model.Customer;
import com.capgemini.repository.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import static com.capgemini.common.Constants.*;

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
