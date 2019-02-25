package com.accenture.accountservice;

import com.accenture.accountservice.dto.response.AddressResponse;
import com.accenture.accountservice.dto.response.CustomerResponse;
import com.accenture.accountservice.entity.Account;
import com.accenture.accountservice.entity.Address;
import com.accenture.accountservice.entity.CreditCard;
import com.accenture.accountservice.entity.Customer;
import com.accenture.accountservice.enums.AddressType;
import com.accenture.accountservice.enums.CreditCardType;
import com.accenture.accountservice.messaging.MessageProducer;
import com.accenture.accountservice.repository.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.FutureTask;

@SpringBootApplication
@EnableDiscoveryClient
public class AccountServiceApplication {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private MessageProducer customerMessageProducer;

	@Autowired
	private ModelMapper modelMapper;


	public static void main(String[] args) {
		SpringApplication.run(AccountServiceApplication.class, args);

	}

	@PostConstruct
	public void sendCustomer() {
		Account account = new Account("12345");
		Customer customer = new Customer("Semen", "Shylev", "west040594@gmail.com", account);
		CreditCard creditCard = new CreditCard("1234567890", CreditCardType.Visa);
		customer.getAccount().getCreditCards().add(creditCard);



		String street1 = "My Street1";
		Address address = new Address(street1, null, "Tver", "Tver", "Russia", 170021, AddressType.SHIPPING);

		customer.getAccount().getAddresses().add(address);

		customer = customerRepository.save(customer);


		CustomerResponse customerResponse = modelMapper.map(customer, CustomerResponse.class);
		AddressResponse addressResponse = modelMapper.map(address, AddressResponse.class);
		customerMessageProducer.sendCustomerMessage(customerResponse);
		customerMessageProducer.sendAddressMessage(addressResponse);

	}

}

