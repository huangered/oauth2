package com.yih.demo.pojo;

import org.springframework.security.core.GrantedAuthority;

public class DummyGrantedAuthority implements GrantedAuthority {
    @Override
    public String getAuthority() {
        return "READ";
    }
}
