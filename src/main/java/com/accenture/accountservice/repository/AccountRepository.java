package com.accenture.accountservice.repository;

import com.accenture.accountservice.entity.Account;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface AccountRepository extends PagingAndSortingRepository<Account, UUID> {

}
