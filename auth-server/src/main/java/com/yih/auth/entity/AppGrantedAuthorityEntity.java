package com.yih.auth.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Data
@Entity
public class AppGrantedAuthorityEntity {
    @Id
    @SequenceGenerator(name = "app_granted_authority_entity_id_generator", sequenceName = "app_granted_authority_entity_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "app_granted_authority_entity_id_generator")
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
