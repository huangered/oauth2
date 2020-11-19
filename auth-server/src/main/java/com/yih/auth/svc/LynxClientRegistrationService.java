package com.yih.auth.svc;

import org.springframework.security.oauth2.provider.ClientAlreadyExistsException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.NoSuchClientException;

import java.util.List;

public interface LynxClientRegistrationService {

	void addClientDetails(Long userId, ClientDetails clientDetails) throws ClientAlreadyExistsException;

	void updateClientDetails(Long userId, ClientDetails clientDetails) throws NoSuchClientException;

	void updateClientSecret(Long userId, String clientId, String secret) throws NoSuchClientException;

	void removeClientDetails(Long userId, String clientId) throws NoSuchClientException;
	
	List<ClientDetails> listClientDetails(Long userId);
}
