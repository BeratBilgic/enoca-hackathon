package com.example.customeraddress.dto.converter;

import com.example.customeraddress.dto.CustomerDto;
import com.example.customeraddress.model.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerDtoConverter {

    public CustomerDto convertToCustomerDto(Customer from){
        return CustomerDto.builder()
                .id(from.getId())
                .email(from.getEmail())
                .addresses(from.getAddresses())
                .build();
    }
}
