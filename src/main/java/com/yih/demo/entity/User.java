package com.yih.demo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Setter
@Getter
public class User {
    @Id
    private Long id;

    private String username;

    private String password;
}
