package com.github.yildizmy.dto.response;

import com.github.yildizmy.model.Customer;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

/**
 * Data Transfer Object used for returning Customer data
 */
@Data
public class CustomerResponse {

    private Long id;

    private String name;

    private String surname;

    private String email;

    private BigDecimal balance;

    private Set<TransactionResponse> transactions;

    public CustomerResponse(Customer customer) {
        this(customer, null, null);
    }

    public CustomerResponse(Customer customer, BigDecimal balance, Set<TransactionResponse> transactions) {
        this.id = customer.getId();
        this.name = customer.getName();
        this.surname = customer.getSurname();
        this.email = customer.getEmail();
        this.balance = balance;
        this.transactions = transactions;
    }
}
