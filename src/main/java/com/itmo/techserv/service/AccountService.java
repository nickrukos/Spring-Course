package com.itmo.techserv.service;

import com.itmo.techserv.constants.UserType;
import com.itmo.techserv.entity.Roles;
import com.itmo.techserv.entity.Users;
import com.itmo.techserv.exceptions.AccountException;
import com.itmo.techserv.repository.RoleRepository;
import com.itmo.techserv.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
 //   private final JwtSecurityService jwtSecurityService;
    private final AuthenticationManager authenticationManager;

    public AccountService(UserRepository userRepository,
                          RoleRepository roleRepository,
                          PasswordEncoder passwordEncoder,
 //                        JwtSecurityService jwtSecurityService,
                          AuthenticationManager authenticationManager) {
        this.passwordEncoder = passwordEncoder;
 //     this.jwtSecurityService = jwtSecurityService;
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }
    public void registration(Users user) throws AccountException {
        if (userRepository.existsByUserName(user.getUserName())) {
            throw new AccountException("Username is already taken");
        }
        roleRepository.findByUserType(UserType.USER)
                .ifPresentOrElse(user::setUserRole,
                        () -> {
                            Roles userRole = new Roles();
                            userRole.setUserType(UserType.USER);
                            user.setUserRole(userRole);
                            roleRepository.save(userRole);
                        }
                );
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

}
