package com.yih.auth.svc;

import com.yih.auth.pojo.oauth2.AppClient;
import org.springframework.security.oauth2.provider.ClientAlreadyExistsException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.NoSuchClientException;

import java.util.List;

public interface LynxOauth2ClientService {

    void addClientDetails(Long userId, ClientDetails clientDetails) throws ClientAlreadyExistsException;

    AppClient updateClientDetails(Long userId, ClientDetails clientDetails) throws NoSuchClientException;

    AppClient updateClientSecret(Long userId, String clientName) throws NoSuchClientException;

    void removeClientDetails(Long userId, String clientId) throws NoSuchClientException;

    List<ClientDetails> listClientDetails(Long userId);
}
