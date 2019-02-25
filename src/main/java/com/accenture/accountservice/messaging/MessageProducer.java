package com.accenture.accountservice.messaging;

import com.accenture.accountservice.dto.response.AddressResponse;
import com.accenture.accountservice.dto.response.CustomerResponse;

public interface MessageProducer {
    void sendCustomerMessage(CustomerResponse data);
    void sendAddressMessage(AddressResponse data);
}
