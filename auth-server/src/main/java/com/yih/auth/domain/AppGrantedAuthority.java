package com.yih.auth.domain;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

@Data
public class AppGrantedAuthority implements GrantedAuthority {
    private String authority;

    public AppGrantedAuthority(String s) {
        this.authority = s;
    }
}