package com.yih.auth.entity;

import lombok.Data;

import javax.persistence.*;

@Table(name = "users", schema = "auth")
@Data
@Entity
public class UserEntity {
    @Id
    @SequenceGenerator(name = "user_id_generator", sequenceName = "user_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "user_id_generator")
    private Long id;

    @Column(unique = true)
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
    }
}