package com.taisiia.shop.security;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

//klasy pomocnicze dla metod statycznych
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SecurityUtils {

    public static String getCurrentUserName() {
       var authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication == null ? null : authentication.getName();
    }
}
