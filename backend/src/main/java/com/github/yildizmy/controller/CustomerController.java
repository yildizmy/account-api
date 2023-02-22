package com.github.yildizmy.controller;

import com.github.yildizmy.dto.request.CustomerRequest;
import com.github.yildizmy.dto.response.ApiResponse;
import com.github.yildizmy.dto.response.CommandResponse;
import com.github.yildizmy.dto.response.CustomerResponse;
import com.github.yildizmy.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Clock;
import java.time.Instant;
import java.util.List;

import static com.github.yildizmy.common.Constants.SUCCESS;

/**
 * Endpoint for Customer related requests
 */
@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final Clock clock;
    private final CustomerService customerService;

    /**
     * Fetches a customer by the given id
     *
     * @param id
     * @return a single customer
     */
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CustomerResponse>> findById(@PathVariable long id) {
        final CustomerResponse response = customerService.findById(id);
        return ResponseEntity.ok(new ApiResponse<>(Instant.now(clock).toEpochMilli(), SUCCESS, response));
    }

    /**
     * Fetches all customers with account and transaction data
     *
     * @return customer list
     */
    @GetMapping
    public ResponseEntity<ApiResponse<List<CustomerResponse>>> findAll() {
        final List<CustomerResponse> response = customerService.findAll();
        return ResponseEntity.ok(new ApiResponse<>(Instant.now(clock).toEpochMilli(), SUCCESS, response));
    }

    /**
     * Creates a new customer using the given request parameters
     *
     * @param request
     * @return id of created customer
     */
    @PostMapping
    public ResponseEntity<ApiResponse<CommandResponse>> create(@Valid @RequestBody CustomerRequest request) {
        final CommandResponse response = customerService.create(request);
        return ResponseEntity.ok(new ApiResponse<>(Instant.now().toEpochMilli(), SUCCESS, response));
    }
}
