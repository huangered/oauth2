package com.yih.auth.domain;

import com.yih.auth.entity.UserEntity;
import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class AppUser implements UserDetails {

    private Long id;
    private String username;
    private String password;
    private boolean enabled;
    private boolean credentialsNonExpired;
    private boolean accountNonLocked;
    private boolean accountNonExpired;
    private String telephone;
    private List<AppGrantedAuthority> authorities;

    public AppUser(UserEntity userEntity, List<String> authorities){
        this.id = userEntity.getId();
        this.username = userEntity.getUsername();
        this.password = userEntity.getPassword();
        this.enabled = userEntity.isEnabled();
        this.credentialsNonExpired = userEntity.isCredentialsNonExpired();
        this.accountNonExpired = userEntity.isAccountNonExpired();
        this.accountNonLocked = userEntity.isAccountNonLocked();
        this.authorities = authorities.stream().map(AppGrantedAuthority::new).collect(Collectors.toList());;
    }

    public boolean create(){
        return true;
    }

    public void update(){

    }
}
