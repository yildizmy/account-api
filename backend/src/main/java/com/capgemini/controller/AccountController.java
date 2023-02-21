package com.capgemini.controller;

import com.capgemini.dto.request.AccountRequest;
import com.capgemini.dto.response.AccountResponse;
import com.capgemini.dto.response.ApiResponse;
import com.capgemini.dto.response.CommandResponse;
import com.capgemini.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Clock;
import java.time.Instant;
import java.util.List;

import static com.capgemini.common.Constants.SUCCESS;

/**
 * Endpoint for Account related requests
 */
@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final Clock clock;
    private final AccountService accountService;

    /**
     * Fetches all customers with account and transaction data
     *
     * @return customer list
     */
    @GetMapping
    public ResponseEntity<ApiResponse<List<AccountResponse>>> findAll() {
        final List<AccountResponse> response = accountService.findAll();
        return ResponseEntity.ok(new ApiResponse<>(Instant.now(clock).toEpochMilli(), SUCCESS, response));
    }

    /**
     * Opens a new current account for the given customer
     *
     * @param request
     * @return
     */
    @PostMapping
    public ResponseEntity<ApiResponse<CommandResponse>> create(@Valid @RequestBody AccountRequest request) {
        final CommandResponse response = accountService.create(request.getCustomerId(), request.getBalance());
        return ResponseEntity.ok(new ApiResponse<>(Instant.now(clock).toEpochMilli(), SUCCESS, response));
    }
}
