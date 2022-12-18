package com.example.customeraddress.dto.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class SignUpRequest {
    private String email;
    private String password;
}
