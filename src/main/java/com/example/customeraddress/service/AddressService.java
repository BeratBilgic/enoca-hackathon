package com.example.customeraddress.service;

import com.example.customeraddress.dto.AddressDto;
import com.example.customeraddress.dto.converter.AddressDtoConverter;
import com.example.customeraddress.dto.request.CreateAddressRequest;
import com.example.customeraddress.dto.request.UpdateAddressRequest;
import com.example.customeraddress.exception.GenericException;
import com.example.customeraddress.model.Address;
import com.example.customeraddress.model.Customer;
import com.example.customeraddress.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final CustomerService customerService;
    private final AddressRepository repository;
    private final AddressDtoConverter dtoConverter;

    public Boolean existsByName(String name){
        return repository.existsByName(name);
    }

    public AddressDto createAddress(CreateAddressRequest request){
        var isAlreadyExists = existsByName(request.getName());

        if(isAlreadyExists) {
            throw GenericException.builder()
                    .httpStatus(HttpStatus.FOUND)
                    .errorMessage("Address name: " + request.getName() + " is already used").build();
        }

        Customer customer = customerService.getCustomerInTokenContext();

        Address address = new Address().builder()
                .name(request.getName())
                .city(request.getCity())
                .district(request.getDistrict())
                .customer(customer)
                .build();

        customer.getAddresses().add(address);

        return dtoConverter.convertToAddressDto(repository.save(address));
    }

    protected Address findAddressById(Long Id){
        return repository.findById(Id).orElseThrow(
                () -> GenericException.builder()
                        .httpStatus(HttpStatus.NOT_FOUND)
                        .errorMessage("Id: "+ Id + " address not found")
                        .build());
    }

    public AddressDto getAddress(Long Id){
        Customer customer = customerService.getCustomerInTokenContext();
        Address address = findAddressById(Id);

        if (address.getCustomer().getId() != customer.getId())
            throw GenericException.builder()
                    .httpStatus(HttpStatus.UNAUTHORIZED)
                    .errorMessage("{"+customer.getEmail()+"} you can only get your own address")
                    .build();

        return dtoConverter.convertToAddressDto(address);
    }

    public List<AddressDto> getAllAddress() {
        Customer customer = customerService.getCustomerInTokenContext();

        return customer.getAddresses()
                .stream()
                .map(dtoConverter::convertToAddressDto)
                .collect(Collectors.toList());
    }

    public AddressDto updateAddress(Long id, UpdateAddressRequest request){
        Customer customer = customerService.getCustomerInTokenContext();

        Address address = findAddressById(id);

        if (address.getCustomer().getId() != customer.getId())
            throw GenericException.builder()
                    .httpStatus(HttpStatus.UNAUTHORIZED)
                    .errorMessage("{"+customer.getEmail()+"} you can only update your own address")
                    .build();

        var isAlreadyExists = existsByName(request.getName());

        if(isAlreadyExists) {
            throw GenericException.builder()
                    .httpStatus(HttpStatus.FOUND)
                    .errorMessage("Address name: " + request.getName() + " is already used").build();
        }

        address.setName(request.getName());
        address.setCity(request.getCity());
        address.setDistrict(request.getDistrict());
        address.setCustomer(customer);

        return dtoConverter.convertToAddressDto(repository.save(address));
    }

    public void deleteAddress(Long Id){
        Customer customer = customerService.getCustomerInTokenContext();
        Address address = findAddressById(Id);

        if (address.getCustomer().getId() != customer.getId())
            throw GenericException.builder()
                    .httpStatus(HttpStatus.UNAUTHORIZED)
                    .errorMessage("{"+customer.getEmail()+"} you can only delete your own address")
                    .build();

        repository.delete(address);
    }
}
