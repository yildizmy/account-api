package com.capgemini.dto.mapper;

import com.capgemini.dto.request.CustomerRequest;
import com.capgemini.model.Customer;
import org.mapstruct.Mapper;

/**
 * Mapper for CustomerRequest
 */
@Mapper(componentModel = "spring")
public interface CustomerRequestMapper {

    Customer toEntity(CustomerRequest source);

    CustomerRequest toDto(Customer destination);
}
