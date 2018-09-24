package com.piggybank;

import com.piggybank.model.Account;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.List;

@Service
public class AccountService {

    String ACCOUNT_SERVICE_BASE_URL = "http://localhost:9000/api/v1";

    public List<Account> getCustomerCard(String searchString) {
        RestTemplate restTemplate = new RestTemplate();
        String accountsResourceUrl = ACCOUNT_SERVICE_BASE_URL + "/search-accounts";
        HashMap<String,String> params = new HashMap<>();
        if(searchString.contains("@")) {
            params.put("email", searchString);
        } else {
            params.put("lastName" , searchString);
        }
       String uri=  UriComponentsBuilder.fromHttpUrl(accountsResourceUrl).queryParam("email",searchString).build().toUriString();
        ResponseEntity<List<Account>> response = restTemplate.exchange(uri,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Account>>(){});
        List<Account> accounts = response.getBody();
        return  accounts;
    }
}
