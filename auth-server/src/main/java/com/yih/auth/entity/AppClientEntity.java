package com.yih.auth.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Data
@Entity
public class AppClientEntity {
    @Id
    @SequenceGenerator(name = "app_client_entity_id_generator", sequenceName = "app_client_entity_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "app_client_entity_id_generator")
    private Long id;

    private String clientId;

    private String clientSecret;

    private String scopeList;

    private String authorizedGrantTypesList;

    private String registeredRedirectUrisList;

    private Integer accessTokenValiditySeconds;

    private Integer refreshTokenValiditySeconds;

    private boolean autoApprove;

    public AppClientEntity() {
    }


}
