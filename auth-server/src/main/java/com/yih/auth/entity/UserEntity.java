package com.yih.auth.entity;

import com.yih.auth.domain.AppUser;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class UserEntity {
    @Id
    private Long id;

    private String username;
    private String password;

    private boolean enabled;
    private boolean credentialsNonExpired;
    private boolean accountNonLocked;
    private boolean accountNonExpired;
    private String telephone;

    public UserEntity(){}

    public UserEntity(String username, String encodedPassword) {
        this.username = username;
        this.password = encodedPassword;
        this.enabled = true;
        this.credentialsNonExpired = true;
        this.accountNonLocked = true;
        this.accountNonExpired = true;;
    }
}
