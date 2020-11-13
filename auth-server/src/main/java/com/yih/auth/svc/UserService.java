package com.yih.auth.svc;

import com.yih.auth.domain.user.RegisterUser;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserService {
    Optional<UserDetails> findByUsername(String username);

    /**
     * create new user
     *
     * @param appUser
     * @return
     */
    long create(RegisterUser appUser);
}
