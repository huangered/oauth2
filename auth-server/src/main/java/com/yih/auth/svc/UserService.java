package com.yih.auth.svc;

import com.yih.auth.domain.AppUser;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserService {
    Optional<UserDetails> findByUsername(String username);
    boolean create(AppUser appUser);
}
