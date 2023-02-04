package com.example.customeraddress.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {
    private Long id;

    private LocalDateTime creationDate;

    private LocalDateTime updateDate;

    private String name;

    private String city;

    private String district;

    private AddressCustomerDto customer;
}
