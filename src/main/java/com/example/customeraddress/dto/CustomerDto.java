package com.example.customeraddress.dto;

import com.example.customeraddress.model.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {
    private Long id;

    private LocalDateTime creationDate;

    private LocalDateTime updateDate;

    private String email;

    private String password;

    private Set<CustomerAddressDto> addresses;
}
