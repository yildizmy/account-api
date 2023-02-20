package com.capgemini.dto.response;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;

/**
 * Data Transfer Object used for returning id value of the saved entity
 */
@Value
@RequiredArgsConstructor
@Builder
public class CommandResponse {
    Long id;
}
