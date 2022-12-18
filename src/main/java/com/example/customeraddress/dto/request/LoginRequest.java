package com.example.customeraddress.dto.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class LoginRequest {
    private String email;
    private String password;
}
