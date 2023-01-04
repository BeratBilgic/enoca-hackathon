package com.example.customeraddress.dto.converter;

import com.example.customeraddress.dto.CustomerAddressDto;
import com.example.customeraddress.model.Address;
import org.springframework.stereotype.Component;

@Component
public class CustomerAddressDtoConverter {

    public CustomerAddressDto convertToDto(Address from){
        return CustomerAddressDto.builder()
                .id(from.getId())
                .name(from.getName())
                .city(from.getCity())
                .district(from.getDistrict())
                .build();
    }
}
