package com.github.yildizmy.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Value;

import static com.github.yildizmy.common.Constants.EMAIL_NOT_VALID;

/**
 * Data Transfer Object for CustomerRequest
 */
@Value
public class CustomerRequest {

    @Size(min = 3, max = 50)
    @NotBlank
    private String name;

    @Size(min = 3, max = 50)
    @NotBlank
    private String surname;

    @Email(message = EMAIL_NOT_VALID)
    @Size(min = 5, max = 50)
    private String email;
}
