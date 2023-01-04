package com.example.customeraddress.dto.request;

import lombok.*;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class SignUpRequest {
    @NotBlank
    private String email;
    @NotBlank
    private String password;
}
