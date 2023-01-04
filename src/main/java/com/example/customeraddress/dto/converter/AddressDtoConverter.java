package com.example.customeraddress.dto.converter;

import com.example.customeraddress.dto.AddressDto;
import com.example.customeraddress.model.Address;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AddressDtoConverter {

    private final AddressCustomerDtoConverter addressCustomerDtoConverter;

    public AddressDto convertToAddressDto(Address from){
        return AddressDto.builder()
                .id(from.getId())
                .name(from.getName())
                .city(from.getCity())
                .district(from.getDistrict())
                .customer(addressCustomerDtoConverter.convertToDto(from.getCustomer()))
                .build();
    }
}
