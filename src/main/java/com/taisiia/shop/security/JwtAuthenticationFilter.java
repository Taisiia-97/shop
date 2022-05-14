package com.taisiia.shop.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taisiia.shop.domain.dto.AuthenticationDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClaims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.stream.Collectors;

@Slf4j
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final ObjectMapper objectMapper;



    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, ObjectMapper objectMapper) {
        super(authenticationManager);
        this.objectMapper = objectMapper;
        setFilterProcessesUrl("/api/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (!request.getMethod().equals(HttpMethod.POST.toString())) {
            throw new AuthenticationServiceException("Not valid http method your method is: " + request.getMethod());
        }
        try {
            AuthenticationDto authenticationDto = objectMapper.readValue(request.getInputStream(), AuthenticationDto.class);
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(authenticationDto.getLogin(), authenticationDto.getPassword());
            return getAuthenticationManager().authenticate(usernamePasswordAuthenticationToken);
        } catch (IOException e) {
            throw new AuthenticationServiceException(e.getMessage());
        }

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        //na podstawie danych z tej klasy będzie generowany token jwt
        Claims claims = new DefaultClaims()
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .setSubject(authResult.getName());
        String roles = authResult.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        claims.put("authorities", roles);
        String token = Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, "zlotowka")
                .compact();
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        //będzie zwraca użykownikowi
        objectMapper.writeValue(response.getWriter(), Collections.singletonMap("token", token));
    }


}
