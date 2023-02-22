package com.github.yildizmy.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.math.BigDecimal;

import static com.github.yildizmy.common.Constants.INITIAL_AMOUNT_EQUAL_OR_GREATER_ZERO;

/**
 * Data Transfer Object for AccountRequest
 */
@Value
public class AccountRequest {

    @NotNull
    Long customerId;

    @NotNull(message = INITIAL_AMOUNT_EQUAL_OR_GREATER_ZERO)
    @Min(value = 0, message = INITIAL_AMOUNT_EQUAL_OR_GREATER_ZERO)
    BigDecimal balance;
}
