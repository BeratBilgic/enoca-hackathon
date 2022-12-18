package com.example.customeraddress.service;

import com.example.customeraddress.model.Customer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final CustomerService customerService;
    private final Collection<SimpleGrantedAuthority> authoritiesList;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Customer customer = customerService.findCustomerByEmail(email);
        authoritiesList.add(new SimpleGrantedAuthority("customer"));
        return new org.springframework.security.core.userdetails.User(customer.getEmail(),
                customer.getPassword(),
                authoritiesList);
    }
}
