package com.yih.auth.svc.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.yih.auth.pojo.oauth2.AppClient;
import com.yih.auth.entity.AppClientEntity;
import com.yih.auth.mapper.LynxClientMapper;
import com.yih.auth.repo.AppClientRepo;
import com.yih.auth.svc.LynxOauth2ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Component;

import java.util.*;

@Slf4j
@Component("JpaClientDetailsService")
public class JpaOauth2ClientDetailsServiceImpl implements ClientDetailsService, LynxOauth2ClientService {
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
    public AppClient updateClientSecret(Long userId, String clientName) throws NoSuchClientException {
        Optional<AppClientEntity> entity = repo.findByUserIdAndClientName(userId, clientName);
        if (entity.isPresent()) {
            entity.get().setClientId(UUID.randomUUID().toString());
            entity.get().setClientSecret(UUID.randomUUID().toString());
            AppClientEntity re = repo.save(entity.get());
            return LynxClientMapper.instance.entityToPojo(re);
        } else {
            throw new NoSuchClientException(clientName);
        }
    }

    @Override
    public void removeClientDetails(Long userId, String clientName) throws NoSuchClientException {
        repo.deleteByUserIdAndClientName(userId, clientName);
    }

    @Override
    public List<ClientDetails> listClientDetails(Long userId) {
        return null;
    }
}