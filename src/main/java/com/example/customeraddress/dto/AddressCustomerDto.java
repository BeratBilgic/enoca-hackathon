package com.example.customeraddress.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressCustomerDto {
    private Long id;

    private LocalDateTime creationDate;

    private LocalDateTime updateDate;

    private String email;

    private String password;
}
