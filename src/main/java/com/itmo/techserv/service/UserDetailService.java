package com.itmo.techserv.service;

import java.util.Set;

import com.itmo.techserv.entity.Users;
import com.itmo.techserv.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    public UserDetailService(UserRepository userRepository ) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepository.findByUserName(username)
                .orElseThrow(()->new UsernameNotFoundException("ApplicationUser not found"));

        GrantedAuthority authority = new SimpleGrantedAuthority(
                user.getUserRole().getUserType().name());
        System.out.println(authority.getAuthority());

        return new User(username, user.getPassword(), Set.of(authority));
    }
}
