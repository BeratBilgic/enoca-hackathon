package com.example.customeraddress.dto.converter;

import com.example.customeraddress.dto.AddressDto;
import com.example.customeraddress.model.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressDtoConverter {

    public AddressDto convertToAddressDto(Address from){
        return AddressDto.builder()
                .id(from.getId())
                .name(from.getName())
                .city(from.getCity())
                .district(from.getDistrict())
                .customer(from.getCustomer())
                .build();
    }
}
