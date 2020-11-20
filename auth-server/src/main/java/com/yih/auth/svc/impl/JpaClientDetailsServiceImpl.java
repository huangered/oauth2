package com.yih.auth.svc.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.yih.auth.domain.oauth2.AppClient;
import com.yih.auth.entity.AppClientEntity;
import com.yih.auth.repo.AppClientRepo;
import com.yih.auth.svc.LynxClientRegistrationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Component;

import java.util.*;

@Slf4j
@Component("JpaClientDetailsService")
public class JpaClientDetailsServiceImpl implements ClientDetailsService, LynxClientRegistrationService {
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
            bcd.setRegisteredRedirectUri(Sets.newHashSet(ac.get().getRegisteredRedirectUris()));
            bcd.setScope(Lists.newArrayList(ac.get().getScopeList().split(",")));
            return bcd;
        }
        throw new ClientRegistrationException("client id not found");
    }

    @Override
    public void addClientDetails(Long userId, ClientDetails clientDetails) throws ClientAlreadyExistsException {

        AppClientEntity entity = new AppClientEntity(clientDetails, userId);
        repo.save(entity);
    }

    @Override
    public AppClient updateClientDetails(Long userId, ClientDetails clientDetails) throws NoSuchClientException {
        return null;
    }

    @Override
    public AppClient updateClientSecret(Long userId, String clientId, String secret) throws NoSuchClientException {
        return null;
    }

    @Override
    public void removeClientDetails(Long userId, String clientId) throws NoSuchClientException {

    }

    @Override
    public List<ClientDetails> listClientDetails(Long userId) {
        return null;
    }
}