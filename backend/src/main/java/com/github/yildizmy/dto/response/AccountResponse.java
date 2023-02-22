package com.github.yildizmy.dto.response;

import com.github.yildizmy.model.Account;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Data Transfer Object used for returning Account data
 */
@Data
public class AccountResponse {

    private Long id;

    private BigDecimal balance;

    private String customerName;

    public AccountResponse(Account account) {
        this.id = account.getId();
        this.balance = account.getBalance();
        this.customerName = String.format("%s %s", account.getCustomer().getName(), account.getCustomer().getSurname());
    }
}
