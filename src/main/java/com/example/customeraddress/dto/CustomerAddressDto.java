package com.example.customeraddress.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerAddressDto {
    private Long id;

    private String name;

    private String city;

    private String district;
}
