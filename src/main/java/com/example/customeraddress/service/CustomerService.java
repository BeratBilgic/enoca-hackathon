package com.example.customeraddress.service;

import com.example.customeraddress.dto.CustomerDto;
import com.example.customeraddress.dto.converter.CustomerDtoConverter;
import com.example.customeraddress.exception.GenericException;
import com.example.customeraddress.model.Customer;
import com.example.customeraddress.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

@Service
@Slf4j
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

    public CustomerDto getCustomerDto(String email) {
        return dtoConverter.convertToCustomerDto(findCustomerByEmail(email));
    }

    public Customer findCustomerById(Long Id) {
        return repository.findById(Id)
                .orElseThrow(notFoundUser(HttpStatus.NOT_FOUND));
    }

    private static Supplier<GenericException> notFoundUser(HttpStatus unauthorized) {
        return () -> GenericException
                .builder()
                .httpStatus(unauthorized)
                .errorMessage("customer not found!")
                .build();
    }
}
