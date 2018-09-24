package com.piggybank.model;

import lombok.Data;

import java.math.BigInteger;

@Data
public class Customer {
    private BigInteger id;
    private String firstName;
    private String lastName;
    EmailAddress emailAddress;
    Address address;

}
