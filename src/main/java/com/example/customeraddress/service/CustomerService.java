package com.example.customeraddress.service;

import com.example.customeraddress.dto.CustomerDto;
import com.example.customeraddress.dto.converter.CustomerDtoConverter;
import com.example.customeraddress.exception.GenericException;
import com.example.customeraddress.model.Customer;
import com.example.customeraddress.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository repository;
    private final CustomerDtoConverter dtoConverter;

    public Customer saveRepository(Customer customer){
        return repository.save(customer);
    }

    public Boolean existsByEmail(String email){
        return repository.existsByEmail(email);
    }

    public Customer findCustomerByEmail(String email) {
        return repository.findCustomerByEmail(email)
                .orElseThrow(notFoundUser(HttpStatus.NOT_FOUND));
    }

    public CustomerDto getCustomerDtoByEmail(String email) {
        return dtoConverter.convertToCustomerDto(findCustomerByEmail(email));
    }

    public Customer getCustomerInTokenContext() {
        final Authentication authentication = Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication()).orElseThrow(notFoundUser(HttpStatus.UNAUTHORIZED));
        final UserDetails details = Optional.ofNullable((UserDetails) authentication.getPrincipal()).orElseThrow(notFoundUser(HttpStatus.UNAUTHORIZED));
        return findCustomerByEmail(details.getUsername());
    }

    private static Supplier<GenericException> notFoundUser(HttpStatus httpStatus) {
        return () -> GenericException
                .builder()
                .httpStatus(httpStatus)
                .errorMessage("customer not found!")
                .build();
    }
}
