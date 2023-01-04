package com.example.customeraddress.dto.converter;

import com.example.customeraddress.dto.AddressCustomerDto;
import com.example.customeraddress.model.Customer;
import org.springframework.stereotype.Component;

@Component
public class AddressCustomerDtoConverter {

    public AddressCustomerDto convertToDto(Customer from){
        return AddressCustomerDto.builder()
                .id(from.getId())
                .email(from.getEmail())
                .password(from.getPassword())
                .build();
    }
}
