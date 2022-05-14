package com.taisiia.shop.controller;

import com.taisiia.shop.domain.dto.AuthenticationDto;
import com.taisiia.shop.domain.dto.TokenDto;
import com.taisiia.shop.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/login")
public class LoginController {
    private final LoginService loginService;

    @PostMapping
    public TokenDto login(@RequestBody AuthenticationDto authenticationDto) {
        return loginService.login(authenticationDto);
    }
}
