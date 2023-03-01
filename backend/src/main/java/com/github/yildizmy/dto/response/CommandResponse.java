package com.github.yildizmy.dto.response;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * Data Transfer Object used for returning id value of the saved entity
 */
@Data
@RequiredArgsConstructor
@Builder
public class CommandResponse {
    Long id;
}
