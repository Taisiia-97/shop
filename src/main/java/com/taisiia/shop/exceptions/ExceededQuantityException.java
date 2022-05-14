package com.taisiia.shop.exceptions;

public class ExceededQuantityException extends RuntimeException {
    public ExceededQuantityException(String message) {
        super(message);
    }
}
