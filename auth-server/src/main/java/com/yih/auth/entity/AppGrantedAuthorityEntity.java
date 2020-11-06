package com.yih.auth.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class AppGrantedAuthorityEntity {
    @Id
    private Long id;
    private Long userId;
    private String authority;

    public AppGrantedAuthorityEntity(Long userId, String authority) {
        this.userId = userId;
        this.authority = authority;
    }
}
