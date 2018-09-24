package com.piggybank;

import com.piggybank.model.Account;
import com.piggybank.model.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Controller
public class AccountController {

    @Autowired
    AccountService accountService;

    @Value("${spring.application.name}")
    String appName;

    @PostMapping("/")
    public String homePagePost(Model model , @ModelAttribute Search search) {
        System.out.println("searchText " + search);
        if(search != null && search.getText().length() > 0) {
            List<Account> accountList = accountService.getCustomerCard(search.getText());
            model.addAttribute("accounts",accountList);
        }
        model.addAttribute("search" , search);
        return "home";
    }

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("search" , new Search());
        return "home";
    }


    @GetMapping("/customer-details/{id}")
    public String customerDetails(Model model) {
        model.addAttribute("appName", appName);
        return "home";
    }


    @GetMapping("/account-transactions/{id}")
    public String accountTransactions(Model model) {
        model.addAttribute("appName", appName);
        return "home";
    }

}