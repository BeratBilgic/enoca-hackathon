package com.example.customeraddress.controller;

import com.example.customeraddress.dto.AddressDto;
import com.example.customeraddress.dto.request.CreateAddressRequest;
import com.example.customeraddress.dto.request.UpdateAddressRequest;
import com.example.customeraddress.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/address")
@RequiredArgsConstructor
public class AddressController {
    private final AddressService addressService;

    @PostMapping("/create")
    public ResponseEntity<AddressDto> createAddress(@Valid @RequestBody CreateAddressRequest request){
        return ResponseEntity.ok(addressService.createAddress(request));
    }

    @GetMapping("/{Id}")
    public ResponseEntity<AddressDto> getAddressByName(@PathVariable Long Id){
        return ResponseEntity.ok(addressService.getAddress(Id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<AddressDto>> getAllAddress(){
        return ResponseEntity.ok(addressService.getAllAddress());
    }

    @PutMapping("/update")
    public ResponseEntity<AddressDto> updateAddress(@Valid @RequestBody UpdateAddressRequest request){
        return ResponseEntity.ok(addressService.updateAddress(request));
    }

    @DeleteMapping("/delete/{Id}")
    public void delete(@PathVariable Long Id){
        addressService.deleteAddress(Id);
    }
}
