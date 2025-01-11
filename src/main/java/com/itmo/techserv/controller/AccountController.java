package com.itmo.techserv.controller;

import com.itmo.techserv.dto.UserRequestDTO;
import com.itmo.techserv.entity.Token;
import com.itmo.techserv.entity.Users;
import com.itmo.techserv.exceptions.AccountException;
import com.itmo.techserv.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

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
    public Token login(@RequestBody UserRequestDTO userRequestDTO){
        try {
            return accountService.loginAccount(userRequestDTO.userName(), userRequestDTO.password());
        } catch (AccountException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

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
