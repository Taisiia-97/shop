package com.taisiia.shop.helper;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AppHelper {

    public String generateRandomString() {
        return UUID.randomUUID().toString();
    }
}
