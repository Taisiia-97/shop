package com.taisiia.shop.domain;

import com.taisiia.shop.exceptions.DiscountException;

import java.util.Map;
import java.util.Optional;

public enum DiscountStatus {
    ACTIVE,
    ARCHIVAL,
    WAITING;

    private static final Map<String, DiscountStatus> STATUSES = Map.ofEntries(Map.entry("active", ACTIVE),
            Map.entry("archival", ARCHIVAL), Map.entry("waiting", WAITING));

    public static DiscountStatus safeValueOf(String type) {
        DiscountStatus status = STATUSES.get(type.toLowerCase());
        if (status == null) {
            throw new DiscountException("This status is not correct");
        }
        return status;
    }

    public static boolean containsValue(String status) {
        return STATUSES.containsKey(status.toLowerCase());
    }

    public static Optional<DiscountStatus> valueOfOrEmpty(String status) {
        try {
            return Optional.of(valueOf(status.toUpperCase()));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
