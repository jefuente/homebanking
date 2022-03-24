package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.dtos.AccountDTO;
import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import static java.util.stream.Collectors.toList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AccountController {

        @Autowired
        private AccountRepository accountRepository;

        @RequestMapping("/accounts")
        public List<AccountDTO> getAccounts() {
                return accountRepository.findAll().stream().map(AccountDTO::new).collect(toList());
        }

        @RequestMapping("/accounts/{id}")
        public AccountDTO getAccount(@PathVariable Long id) {
                return accountRepository.findById(id).map(AccountDTO::new).orElse(null);

        }

        @RequestMapping("/current")
        public AccountDTO getAccount(@RequestParam String number, Authentication authentication) {
                Account account = this.accountRepository.findByNumber(authentication.getName());
                return new AccountDTO(account);

        }




}

