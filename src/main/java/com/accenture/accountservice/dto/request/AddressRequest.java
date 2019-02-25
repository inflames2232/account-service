package com.accenture.accountservice.dto.request;

import com.accenture.accountservice.enums.AddressType;
import lombok.Data;

@Data
public class AddressRequest {

    private String street1;

    private String street2;

    private String state;

    private String city;

    private String country;

    private Integer zipCode;

    private AddressType type;

}
