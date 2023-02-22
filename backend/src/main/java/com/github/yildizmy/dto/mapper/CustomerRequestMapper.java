package com.github.yildizmy.dto.mapper;

import com.github.yildizmy.dto.request.CustomerRequest;
import com.github.yildizmy.model.Customer;
import org.mapstruct.Mapper;

/**
 * Mapper for CustomerRequest
 */
@Mapper(componentModel = "spring")
public interface CustomerRequestMapper {

    Customer toEntity(CustomerRequest source);

    CustomerRequest toDto(Customer destination);
}
