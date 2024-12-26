package com.itmo.techserv.config;
import com.itmo.techserv.exceptions.AccountException;
import com.itmo.techserv.service.JwtSecurityService;
import com.itmo.techserv.service.UserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
@Configuration
public class SecurityConfiguration {
    private final UserDetailService userDetailService;
    private final JwtSecurityService jwtSecurityService;

    public SecurityConfiguration(UserDetailService userDetailService, JwtSecurityService jwtSecurityService) {
        this.userDetailService = userDetailService;
        this.jwtSecurityService = jwtSecurityService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws AccountException {
        try {
            return config.getAuthenticationManager();
        } catch (Exception e) {
            throw new AccountException("AuthenticationManager not configured: " + e.getMessage());
        }
    }
}
