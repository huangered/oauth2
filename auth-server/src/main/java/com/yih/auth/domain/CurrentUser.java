package com.yih.auth.domain;

public class CurrentUser {

    private static final ThreadLocal<Long> cu = new ThreadLocal<>();

    public static Long GetCurrentUser() {
        return cu.get() != null ? cu.get() : 0L;
    }

    public static void setCurrentUser(Long userId) {
        cu.set(userId);
    }
}