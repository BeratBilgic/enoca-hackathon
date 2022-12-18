package com.example.customeraddress.dto.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CreateAddressRequest {
    private Long customerId;

    private String name;

    private String city;

    private String district;
}
