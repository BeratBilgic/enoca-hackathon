package com.example.customeraddress.dto.request;

import lombok.*;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CreateAddressRequest {
    @NotNull
    private String name;
    @NotNull
    private String city;
    @NotNull
    private String district;
}
