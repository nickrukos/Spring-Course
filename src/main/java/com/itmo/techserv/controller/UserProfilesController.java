package com.itmo.techserv.controller;

import com.itmo.techserv.dto.UserRequestDTO;
import com.itmo.techserv.repository.UserRepository;
import com.itmo.techserv.service.UserProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RequiredArgsConstructor
@Slf4j
@Validated
@RequestMapping("api/user_profile")
@RestController
public class UserProfilesController {
    private final UserProfileService userProfileService;
    @PostMapping(path = "/register_operator")
    public ResponseEntity<?> RegisterOperator(@Valid @RequestBody UserRequestDTO userRequestDTO){
        URI uri = URI.create("api/user_profile/register_operator?id="
                + userProfileService.RegisterUser(userRequestDTO,"OPERATOR"));
        return ResponseEntity.created(uri).build();
    }
}
