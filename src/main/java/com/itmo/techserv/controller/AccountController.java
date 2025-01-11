package com.itmo.techserv.controller;

import com.itmo.techserv.entity.Users;
import com.itmo.techserv.exceptions.AccountException;
import com.itmo.techserv.service.AccountService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URI;

@Controller
@RequestMapping("/account")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }
    @GetMapping("/registration")
    public String registration(Users user) {
        return "account/registration";
    }

    @PostMapping("/login")
    public String login() {
        return "account/login";
    }

    @PostMapping("/registration")
    public ResponseEntity<Void> createAccount(@RequestBody Users user) {
        try {
            accountService.registration(user);
            return new ResponseEntity<>(HttpStatusCode.valueOf(201));
        } catch (AccountException e) {
            return new ResponseEntity<>(HttpStatusCode.valueOf(500));
        }
    }
}
