package com.piggybank;

import com.piggybank.model.Account;
import com.piggybank.model.Transaction;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;

@Service
public class AccountService {

    String ACCOUNT_SERVICE_BASE_URL = "http://localhost:9000/api/v1";

    public List<Account> getCustomerCard(String searchString) {
        RestTemplate restTemplate = new RestTemplate();
        String accountsResourceUrl = ACCOUNT_SERVICE_BASE_URL + "/search-accounts";
        String name = "";
        if(searchString.contains("@")) {
           name = "email";
        } else { name = "lastName";
        }
       String uri=  UriComponentsBuilder.fromHttpUrl(accountsResourceUrl).queryParam(name,searchString).build().toUriString();
        ResponseEntity<List<Account>> response = restTemplate.exchange(uri,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Account>>(){});
        List<Account> accounts = response.getBody();
        return  accounts;
    }


    public List<Transaction> getTransactions(BigInteger accountId) {
        RestTemplate restTemplate = new RestTemplate();
        String accountsResourceUrl = ACCOUNT_SERVICE_BASE_URL + "/account/transactions";
        String uri=  UriComponentsBuilder.fromHttpUrl(accountsResourceUrl).queryParam("accountId",accountId).build().toUriString();
        ResponseEntity<List<Transaction>> response = restTemplate.exchange(uri,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Transaction>>(){});
        List<Transaction> transactions = response.getBody();
        return  transactions;
    }

    public Account getAccount(BigInteger accountId) {
        RestTemplate restTemplate = new RestTemplate();
        String accountsResourceUrl = ACCOUNT_SERVICE_BASE_URL + "/account";
        String uri=  UriComponentsBuilder.fromHttpUrl(accountsResourceUrl).queryParam("accountId",accountId).build().toUriString();
        ResponseEntity<Account> response = restTemplate.exchange(uri,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Account>(){});
        return response.getBody();
    }
}
