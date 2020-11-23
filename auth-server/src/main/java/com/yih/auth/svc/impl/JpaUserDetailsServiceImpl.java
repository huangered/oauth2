package com.yih.auth.svc.impl;

import com.yih.auth.svc.LynxUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class JpaUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private LynxUserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserDetails> userOp = userService.findByUsername(username);
        return userOp.orElseThrow(() -> new UsernameNotFoundException(username));
    }
}