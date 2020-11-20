package com.yih.auth.svc;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserService {
    Optional<UserDetails> findByUsername(String username);

    /**
     * create new user
     *
     * @return
     */
    long create(String username, String password);
}
