package com.itmo.techserv.service;

import com.nimbusds.jose.JOSEException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.itmo.techserv.constants.UserType;
import com.itmo.techserv.entity.Roles;
import com.itmo.techserv.entity.Token;
import com.itmo.techserv.entity.Users;
import com.itmo.techserv.exceptions.AccountException;
import com.itmo.techserv.exceptions.ServiceException;
import com.itmo.techserv.repository.RoleRepository;
import com.itmo.techserv.repository.UserRepository;
import org.springframework.http.HttpStatus;

import org.springframework.stereotype.Service;

@Service
public class AccountService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtSecurityService jwtSecurityService;
    private final AuthenticationManager authenticationManager;

    public AccountService(UserRepository userRepository,
                          RoleRepository roleRepository,
                          PasswordEncoder passwordEncoder,
                          JwtSecurityService jwtSecurityService,
                          AuthenticationManager authenticationManager) {
        this.passwordEncoder = passwordEncoder;
        this.jwtSecurityService = jwtSecurityService;
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }
    public void registration(Users user) throws AccountException {
        if (userRepository.existsByUserName(user.getUserName())) {
            throw new AccountException("Username is already taken");
        }
        roleRepository.findByUserType(UserType.ROLE_USER)
                .ifPresentOrElse(user::setUserRole,
                        () -> {
                            Roles userRole = new Roles();
                            userRole.setUserType(UserType.ROLE_USER);
                            user.setUserRole(userRole);
                            roleRepository.save(userRole);
                        }
                );
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
    public Token loginAccount(String userName, String password) throws AccountException, ServiceException{
        if(!userRepository.existsByUserNameAndPassword(userName,password))
            throw new ServiceException(HttpStatus.NOT_FOUND,"Такого пользователя не существует");

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(userName, password));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        Token token = new Token();
        try {
            token.setToken(jwtSecurityService.generateToken((UserDetails) authentication.getPrincipal()));
            token.setRefreshToken(jwtSecurityService.generateRefreshToken());
        } catch (JOSEException e) {
            throw new AccountException("Token cannot ne created: " + e.getMessage());
        }
        return token;
    }
}
