package com.yih.auth.pojo.oauth2;

import com.google.common.collect.Sets;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import java.util.*;

@Data
public class AppClient implements ClientDetails {
    private String clientName;
    private String description;

    private String clientId;
    private String clientSecret;
    private Set<String> scope;
    private String redirectUri;
    private Set<String> authorizedGrantTypes;

    public AppClient() {

    }

    @Override
    public Set<String> getResourceIds() {
        return new HashSet<>();
    }

    @Override
    public boolean isSecretRequired() {
        return true;
    }

    @Override
    public boolean isScoped() {
        return true;
    }

    @Override
    public Set<String> getRegisteredRedirectUri() {
        return Sets.newHashSet(redirectUri);
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @Override
    public Integer getAccessTokenValiditySeconds() {
        return 3600;
    }

    @Override
    public Integer getRefreshTokenValiditySeconds() {
        return 3600;
    }

    @Override
    public boolean isAutoApprove(String scope) {
        return false;
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        return new HashMap<>();
    }
}