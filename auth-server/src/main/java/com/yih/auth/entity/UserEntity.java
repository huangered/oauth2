package com.yih.auth.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Data
@Entity
public class UserEntity {
    @Id
    @SequenceGenerator(name = "user_entity_id_generator", sequenceName = "user_entity_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "user_entity_id_generator")
    private Long id;

    private String username;
    private String password;

    private boolean enabled;
    private boolean credentialsNonExpired;
    private boolean accountNonLocked;
    private boolean accountNonExpired;
    private String telephone;

    public UserEntity() {
    }

    public UserEntity(String username, String encodedPassword) {
        this.username = username;
        this.password = encodedPassword;
        this.enabled = true;
        this.credentialsNonExpired = true;
        this.accountNonLocked = true;
        this.accountNonExpired = true;
        ;
    }
}