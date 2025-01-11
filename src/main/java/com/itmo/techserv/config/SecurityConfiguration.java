package com.itmo.techserv.config;

import com.itmo.techserv.exceptions.AccountException;
import com.itmo.techserv.service.JwtAuthenticationFilter;
import com.itmo.techserv.service.UserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static jakarta.servlet.DispatcherType.ERROR;
import static jakarta.servlet.DispatcherType.FORWARD;

@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
@Configuration

public class SecurityConfiguration {
    private final UserDetailService userDetailService;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfiguration(UserDetailService userDetailService,
                                 JwtAuthenticationFilter jwtAuthenticationFilter
    ) {
        this.userDetailService = userDetailService;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
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
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailService);  // для получения пользователя из хранилища
        provider.setPasswordEncoder(passwordEncoder()); // для работы с паролями
        return provider;
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authenticationProvider(authenticationProvider())
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/account/registration", "/account/login")// запросы
                        .not().authenticated()// разрешены всем
                        .requestMatchers("/api/techservice/register", "/api/techservice/edit/services",
                                        "/api/user_profile/register_operator","/api/booking/value")
                        .hasRole("ADMINISTRATOR")
                        .requestMatchers("/api/booking/discount")
                        .hasRole("OPERATOR")
                        .dispatcherTypeMatchers(FORWARD, ERROR).permitAll()
                        //.permitAll()
                        .anyRequest()
                        .authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // запросы необходимо пропускать через фильтр
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
//         return httpSecurity.csrf(AbstractHttpConfigurer::disable)
//                .authenticationProvider(authenticationProvider())
//                .authorizeHttpRequests(authorize -> authorize
//                        .requestMatchers("/account/registration", "/account/login")
//                        .not().authenticated()
//                        .requestMatchers("/api/techservice/register,", "/api/techservice/edit/services",
//                                        "api/user_profile/register_operator","api/booking/value")
//                        .hasRole("ADMINISTRATOR")
//                        .requestMatchers("api/booking/discount")
//                        .hasRole("OPERATOR")
//                        .dispatcherTypeMatchers(FORWARD, ERROR).permitAll()
//                        //.permitAll()
//                        .anyRequest()
//                        .authenticated())
//                .formLogin(form -> form
//                        .usernameParameter("application_user_username") // значение атрибута name в html форме
//                        .passwordParameter("application_user_password") // значение атрибута name в html форме
//                        .loginPage("/account/login") // форма доступна по адресу
//                        .loginProcessingUrl("/account/login") // обработчик, значение атрибута action тега form
//                        .failureUrl("/account/login?failed") // ошибка авторизации
//                        .defaultSuccessUrl("/account") // перенаправление после успешной авторизации
//                        .permitAll())
//                .logout(logout -> logout.logoutUrl("/account/logout") // <a th:href="@{/account/logout}">Выйти</a>
//                        .logoutSuccessUrl("/account/login") // перенаправление после /account/logout
//                        .permitAll())  // [ВЫЙТИ] /account/logout
//                .build();
//    }
}
