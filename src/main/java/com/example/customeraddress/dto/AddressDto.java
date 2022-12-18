package com.example.customeraddress.dto;

import com.example.customeraddress.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {
    private Long id;

    private String name;

    private String city;

    private String district;

    private Customer customer;
}
