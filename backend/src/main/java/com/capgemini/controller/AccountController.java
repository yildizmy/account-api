package com.capgemini.controller;

import com.capgemini.dto.request.AccountRequest;
import com.capgemini.dto.response.ApiResponse;
import com.capgemini.dto.response.CommandResponse;
import com.capgemini.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Clock;
import java.time.Instant;

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
