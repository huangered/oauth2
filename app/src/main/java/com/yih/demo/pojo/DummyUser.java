package com.yih.demo.pojo;

import lombok.Getter;
import lombok.var;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class DummyUser implements UserDetails {
    @Getter
    private final String username;
    @Getter
    private final String password;

    public DummyUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        var list = new ArrayList<DummyGrantedAuthority>();
        list.add(new DummyGrantedAuthority());
        return list;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
