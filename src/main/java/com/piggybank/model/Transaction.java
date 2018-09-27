package com.piggybank.model;

import lombok.Data;

import java.math.BigInteger;
import java.util.Date;

@Data
public class Transaction {

    private BigInteger id;
    private BigInteger accountId;
    private float amount;
    private String type;
    private String category;
    private Date txnDate;

}
