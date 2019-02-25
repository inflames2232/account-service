package com.accenture.accountservice;

import com.accenture.accountservice.dto.response.CustomerResponse;
import com.accenture.accountservice.entity.Account;
import com.accenture.accountservice.entity.Address;
import com.accenture.accountservice.entity.CreditCard;
import com.accenture.accountservice.entity.Customer;
import com.accenture.accountservice.enums.AddressType;
import com.accenture.accountservice.enums.CreditCardType;
import com.accenture.accountservice.messaging.MessageProducer;
import com.accenture.accountservice.repository.CustomerRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AccountServiceApplication.class)
public class AccountServiceApplicationTests {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private MessageProducer customerMessageProducer;

	@Autowired
	private ModelMapper modelMapper;

	@Test
	public void customerTest() {
		Account account = new Account("12345");
		Customer customer = new Customer("Semen", "Shylev", "west040594@gmail.com", account);
		CreditCard creditCard = new CreditCard("1234567890", CreditCardType.Visa);
		customer.getAccount().getCreditCards().add(creditCard);

		String street1 = "My Street1";
		Address address = new Address(street1, null, "Tver", "Tver", "Russia", 170021, AddressType.SHIPPING);

		customer.getAccount().getAddresses().add(address);

		customer = customerRepository.save(customer);

		CustomerResponse customerResponse = modelMapper.map(customer, CustomerResponse.class);
		customerMessageProducer.sendMessage(customerResponse);

		Customer persistedResult = customerRepository.findById(customer.getId()).get();
		Assert.assertNotNull(persistedResult.getAccount());
		Assert.assertNotNull(persistedResult.getCreatedAt());
		Assert.assertNotNull(persistedResult.getUpdatedAt());
	}
	
	@Test
	public void contextLoads() {
	}

}

