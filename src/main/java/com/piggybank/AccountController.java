package com.piggybank;

import com.piggybank.model.Account;
import com.piggybank.model.Search;
import com.piggybank.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Controller
public class AccountController {

    @Autowired
    AccountService accountService;

    @Value("${spring.application.name}")
    String appName;

    @GetMapping("/search")
    public String homePagePost(Model model , @ModelAttribute Search search ) {
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


    @GetMapping("/transactions-details")
    public String accountTransactions(Model model , @RequestParam BigInteger accountId,@ModelAttribute Search search) {
        List<Transaction> transactions = accountService.getTransactions(accountId);
        Account account = accountService.getAccount(accountId);
        model.addAttribute("account",account);
        model.addAttribute("transactions",transactions);
        model.addAttribute("search" , search);
        return "transactions";
    }

}