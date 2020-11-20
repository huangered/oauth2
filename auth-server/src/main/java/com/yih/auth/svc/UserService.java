package com.yih.auth.svc;

import com.yih.auth.domain.user.AppUser;
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

    void remove(Long id);

    void updatePassword(String password);

    AppUser findById(Long userId);
}
