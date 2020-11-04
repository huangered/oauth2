package com.yih.demo.entity;

import com.yih.demo.pojo.RoleEnum;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class RoleEntity {
    @Id
    private Long id;

    private Long userId;

    private RoleEnum role;
}
