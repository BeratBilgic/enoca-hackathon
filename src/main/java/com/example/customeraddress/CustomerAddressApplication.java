package com.example.customeraddress;

import com.example.customeraddress.config.SecurityConfig;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
public class CustomerAddressApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerAddressApplication.class, args);
	}
}
