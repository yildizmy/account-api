package com.capgemini.dto.mapper;

import com.capgemini.dto.request.CustomerRequest;
import com.capgemini.model.Customer;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-02-20T19:17:29+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.6 (Oracle Corporation)"
)
@Component
public class CustomerRequestMapperImpl implements CustomerRequestMapper {

    @Override
    public Customer toEntity(CustomerRequest source) {
        if ( source == null ) {
            return null;
        }

        Customer customer = new Customer();

        customer.setName( source.getName() );
        customer.setSurname( source.getSurname() );
        customer.setEmail( source.getEmail() );

        return customer;
    }

    @Override
    public CustomerRequest toDto(Customer destination) {
        if ( destination == null ) {
            return null;
        }

        String name = null;
        String surname = null;
        String email = null;

        name = destination.getName();
        surname = destination.getSurname();
        email = destination.getEmail();

        CustomerRequest customerRequest = new CustomerRequest( name, surname, email );

        return customerRequest;
    }
}
