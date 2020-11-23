package com.yih.auth.entity;

import lombok.Data;
import org.springframework.security.oauth2.provider.ClientDetails;

import javax.persistence.*;

@Table(name="oauth2_client", schema = "auth",
        uniqueConstraints = {
                @UniqueConstraint(name = "user_id_client_name", columnNames = {"userId", "clientName"})
        })
@Data
@Entity
public class AppClientEntity {
    @Id
    @SequenceGenerator(name = "app_client_id_generator", sequenceName = "app_client_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "app_client_id_generator")
    private Long id;

    private Long userId;

    private String clientName;

    private String description;

    @Column(unique = true)
    private String clientId;

    private String clientSecret;

    private String scopeList;

    private String authorizedGrantTypesList;

    private String registeredRedirectUris;

    private Integer accessTokenValiditySeconds;

    private Integer refreshTokenValiditySeconds;

    private boolean autoApprove;

    public AppClientEntity() {
    }

    public AppClientEntity(ClientDetails clientDetails, Long userId) {
        this.userId = userId;
        this.clientId = clientDetails.getClientId();
        this.clientSecret = clientDetails.getClientSecret();
        this.scopeList = String.join(",", clientDetails.getScope());
        this.authorizedGrantTypesList = String.join(",", clientDetails.getAuthorizedGrantTypes());
        this.registeredRedirectUris = clientDetails.getRegisteredRedirectUri().stream().findFirst().get();
        this.accessTokenValiditySeconds = clientDetails.getAccessTokenValiditySeconds();
        this.refreshTokenValiditySeconds = clientDetails.getRefreshTokenValiditySeconds();
        this.autoApprove = false;
    }
}
