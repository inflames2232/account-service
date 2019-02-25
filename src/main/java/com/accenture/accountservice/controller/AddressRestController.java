package com.accenture.accountservice.controller;

import com.accenture.accountservice.dto.response.AddressResponse;
import com.accenture.accountservice.entity.Account;
import com.accenture.accountservice.entity.Address;
import com.accenture.accountservice.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.mapping.Collection;
import org.modelmapper.ModelMapper;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/address")
public class AddressRestController {

    private final ModelMapper modelMapper;

    private final AccountRepository accountRepository;

    @GetMapping
    public ResponseEntity<Set<AddressResponse>> readAddresses() {
        List<Account> allAccounts = (List<Account>) accountRepository.findAll();
        Set<Address> addressSet = new HashSet<>();
        allAccounts.stream()
                .map(Account::getAddresses)
                .forEach(addressSet::addAll);

        Set<AddressResponse> addressResponseSet = addressSet.stream()
                .map(address -> modelMapper.map(address, AddressResponse.class))
                .collect(Collectors.toSet());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        List<MediaType> mediaTypes = new ArrayList<>();
        mediaTypes.add(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(mediaTypes);

        return new ResponseEntity<>(addressResponseSet, httpHeaders, HttpStatus.OK);

    }
}
