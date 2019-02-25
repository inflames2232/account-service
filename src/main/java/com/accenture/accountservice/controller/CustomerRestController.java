package com.accenture.accountservice.controller;

import com.accenture.accountservice.dto.response.CustomerResponse;
import com.accenture.accountservice.entity.Customer;
import com.accenture.accountservice.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/customer")
public class CustomerRestController {

    private final ModelMapper modelMapper;

    private final CustomerRepository customerRepository;

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> readCustomers() {
        List<Customer> customers = (List<Customer>) customerRepository.findAll();
        List<CustomerResponse> customerResponses = customers.stream()
                .map(customer -> modelMapper.map(customer, CustomerResponse.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(customerResponses);
    }
}
