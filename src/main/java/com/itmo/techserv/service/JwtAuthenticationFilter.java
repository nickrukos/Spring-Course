package com.itmo.techserv.service;

import org.springframework.stereotype.Component;
import java.io.IOException;
import java.text.ParseException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.proc.BadJOSEException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{
    private final JwtSecurityService jwtSecurityService;
    private final UserDetailService userDetailService;

    public JwtAuthenticationFilter(JwtSecurityService jwtSecurityService, UserDetailService userDetailService) {
        this.jwtSecurityService = jwtSecurityService;
        this.userDetailService = userDetailService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, // запрос
                                    HttpServletResponse response, // ответ
                                    FilterChain filterChain // следующий фильтр
    ) throws ServletException, IOException {

        // если запрос содерит загоовок Authorization и текст начинается с Bearer
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer")) {
            filterChain.doFilter(request, response);
            return;
        }

        // извлекаем и валидируем токен
        String jwt = authHeader.substring(7);

        String userName = null;
        try {
            userName = jwtSecurityService.getSubject(jwt);
        } catch (BadJOSEException | ParseException | JOSEException e) {
            throw new IOException(e);
        }
        if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailService.loadUserByUsername(userName);
            try {
                if (jwtSecurityService.isTokenValid(jwt, userDetails)) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,
                            null, userDetails.getAuthorities()
                    );
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            } catch (BadJOSEException | ParseException | JOSEException e) {
                throw new IOException(e);
            }
        }
        filterChain.doFilter(request, response);
    }
}
