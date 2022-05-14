package com.taisiia.shop.service.impl;

import com.taisiia.shop.domain.dto.AuthenticationDto;
import com.taisiia.shop.domain.dto.TokenDto;
import com.taisiia.shop.service.LoginService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClaims;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {
    private final AuthenticationManager authenticationManager;

    @Override
    public TokenDto login(AuthenticationDto authenticationDto) {
        var usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(authenticationDto.getLogin(), authenticationDto.getPassword());
        var authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        Claims claims = new DefaultClaims()
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .setSubject(authentication.getName());
        String roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        claims.put("authorities", roles);
        var token = Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, "zlotowka")
                .compact();

        return new TokenDto(token);
    }
}
