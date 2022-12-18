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
    private final AddressRepository addressRepository;
    private final AddressDtoConverter dtoConverter;

    public AddressDto createAddress(CreateAddressRequest request){
        Customer customer = customerService.findCustomerById(request.getCustomerId());

        Address address = new Address().builder()
                .name(request.getName())
                .city(request.getCity())
                .district(request.getDistrict())
                .customer(customer)
                .build();

        customer.getAddresses().add(address);

        return dtoConverter.convertToAddressDto(addressRepository.save(address));
    }

    protected Address findAddressById(Long Id){
        return addressRepository.findAddressById(Id).orElseThrow(
                () -> GenericException.builder()
                        .httpStatus(HttpStatus.NOT_FOUND)
                        .errorMessage("Id: "+ Id + " address not found")
                        .build());
    }

    public AddressDto getAddress(Long Id){
        return dtoConverter.convertToAddressDto(findAddressById(Id));
    }

    public List<AddressDto> getAllAddress() {
        return addressRepository.findAll()
                .stream()
                .map(dtoConverter::convertToAddressDto)
                .collect(Collectors.toList());
    }

    public AddressDto updateAddress(UpdateAddressRequest request){
        Address address = findAddressById(request.getId());
        address.setCity(request.getCity());
        address.setDistrict(request.getDistrict());

        return dtoConverter.convertToAddressDto(addressRepository.save(address));
    }

    public void deleteAddress(Long Id){
        Address address = findAddressById(Id);

        addressRepository.delete(address);
    }
}
