package com.yih.auth.svc.impl;

import com.yih.auth.entity.AppClientEntity;
import com.yih.auth.repo.AppClientRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Component;

import java.util.*;

@Slf4j
@Component("JpaClientDetailsService")
public class JpaClientDetailsServiceImpl implements ClientDetailsService, ClientRegistrationService {
    @Autowired
    AppClientRepo repo;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {

        Optional<AppClientEntity> ac = repo.findByClientId(clientId);
        if (ac.isPresent()) {
            BaseClientDetails bcd = new BaseClientDetails();
            bcd.setClientId(clientId);
            bcd.setClientSecret(ac.get().getClientSecret());
            bcd.setAccessTokenValiditySeconds(ac.get().getAccessTokenValiditySeconds());
            bcd.setRefreshTokenValiditySeconds(ac.get().getRefreshTokenValiditySeconds());
            bcd.setAdditionalInformation(new HashMap<>());
            bcd.setAuthorizedGrantTypes(Arrays.asList(ac.get().getAuthorizedGrantTypesList().split(",")));
            bcd.setResourceIds(Collections.emptyList());
            bcd.setAuthorities(Collections.emptyList());
            return bcd;
        }
        return null;
    }

    @Override
    public void addClientDetails(ClientDetails clientDetails) throws ClientAlreadyExistsException {

    }

    @Override
    public void updateClientDetails(ClientDetails clientDetails) throws NoSuchClientException {

    }

    @Override
    public void updateClientSecret(String clientId, String secret) throws NoSuchClientException {

    }

    @Override
    public void removeClientDetails(String clientId) throws NoSuchClientException {

    }

    @Override
    public List<ClientDetails> listClientDetails() {
        return null;
    }
}