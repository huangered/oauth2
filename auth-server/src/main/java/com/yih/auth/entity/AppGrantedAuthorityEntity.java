package com.yih.auth.entity;

import lombok.Data;

import javax.persistence.*;

@Table(name = "oauth2_granted_authority",schema = "auth")
@Data
@Entity
public class AppGrantedAuthorityEntity {
    @Id
    @SequenceGenerator(name = "app_granted_authority_id_generator", sequenceName = "app_granted_authority_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "app_granted_authority_id_generator")
    private Long id;
    private Long userId;
    private String authority;

    public AppGrantedAuthorityEntity() {
    }

    public AppGrantedAuthorityEntity(Long userId, String authority) {
        this.userId = userId;
        this.authority = authority;
    }
}
