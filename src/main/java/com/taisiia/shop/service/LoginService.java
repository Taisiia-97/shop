package com.taisiia.shop.service;

import com.taisiia.shop.domain.dto.AuthenticationDto;
import com.taisiia.shop.domain.dto.TokenDto;

public interface LoginService {

    TokenDto login(AuthenticationDto authenticationDto);
}
