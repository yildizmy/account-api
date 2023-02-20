package com.capgemini.dto.response;

import com.capgemini.model.Transaction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Data Transfer Object used for returning Transaction data
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponse {

    private Long id;

    private String description;

    private LocalDateTime date;

    public TransactionResponse(Transaction transaction) {
        this.id = transaction.getId();
        this.description = transaction.getDescription();
        this.date = transaction.getDate();
    }
}
