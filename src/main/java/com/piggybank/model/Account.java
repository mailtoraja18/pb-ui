package com.piggybank.model;

import lombok.Data;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Data
public class Account {
    private BigInteger id;
    Customer customer;
    private String accountType;
}


