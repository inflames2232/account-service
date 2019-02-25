package com.accenture.accountservice.repository;

import com.accenture.accountservice.entity.Customer;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository extends PagingAndSortingRepository<Customer, UUID> {

    Optional<Customer> findByEmailContaining(String email);
}
