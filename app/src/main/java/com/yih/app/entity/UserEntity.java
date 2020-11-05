package com.yih.resource.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Setter
@Getter
public class UserEntity {
    @Id
    private Long id;

    private String username;

    private String password;
}
