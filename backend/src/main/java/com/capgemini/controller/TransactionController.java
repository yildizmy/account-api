package com.capgemini.controller;

import com.capgemini.dto.response.ApiResponse;
import com.capgemini.dto.response.TransactionResponse;
import com.capgemini.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Clock;
import java.time.Instant;
import java.util.List;

import static com.capgemini.common.Constants.SUCCESS;

/**
 * Endpoint for Customer related requests
 */
@RestController
@RequestMapping("/api/v1/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final Clock clock;
    private final TransactionService transactionService;

    /**
     * Fetches all transactions by the given customer
     *
     * @return transaction list
     */
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<List<TransactionResponse>>> findAllByCustomerId(@PathVariable long id) {
        final List<TransactionResponse> response = transactionService.findAllByCustomerId(id);
        return ResponseEntity.ok(new ApiResponse<>(Instant.now(clock).toEpochMilli(), SUCCESS, response));
    }
}
