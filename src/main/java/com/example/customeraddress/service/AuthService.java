package com.example.customeraddress.service;

import com.example.customeraddress.dto.CustomerDto;
import com.example.customeraddress.dto.ErrorCode;
import com.example.customeraddress.dto.TokenResponseDTO;
import com.example.customeraddress.dto.request.LoginRequest;
import com.example.customeraddress.dto.request.SignUpRequest;
import com.example.customeraddress.dto.converter.CustomerDtoConverter;
import com.example.customeraddress.exception.GenericException;
import com.example.customeraddress.model.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final CustomerService customerService;
    private final CustomerDtoConverter dtoConverter;
    private final TokenService tokenService;

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder encoder;

    public TokenResponseDTO login(LoginRequest loginRequest) {
        try {
            Authentication auth = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
            return TokenResponseDTO
                    .builder()
                    .accessToken(tokenService.generateToken(auth))
                    .customerDto(customerService.getCustomerDto(loginRequest.getEmail()))
                    .build();
        } catch (final BadCredentialsException badCredentialsException) {
            throw GenericException.builder()
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .errorCode(ErrorCode.CUSTOMER_NOT_FOUND)
                    .errorMessage("Invalid email or Password")
                    .build();
        }
    }

    @Transactional
    public CustomerDto signup(SignUpRequest signUpRequest){
        var isAlreadyRegistered = customerService.existsByEmail(signUpRequest.getEmail());

        if(isAlreadyRegistered) {
            throw GenericException.builder().httpStatus(HttpStatus.FOUND)
                    .errorMessage(signUpRequest.getEmail() + "is already used").build();
        }

        var customer = Customer.builder()
                .email(signUpRequest.getEmail())
                .password(encoder.encode(signUpRequest.getPassword()))
                .build();

        return dtoConverter.convertToCustomerDto(customerService.saveRepository(customer));
    }

}
