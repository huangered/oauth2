package com.yih.resource.entity;

import com.yih.resource.pojo.RoleEnum;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class RoleEntity {
    @Id
    private Long id;

    private Long userId;

    private RoleEnum role;
}
