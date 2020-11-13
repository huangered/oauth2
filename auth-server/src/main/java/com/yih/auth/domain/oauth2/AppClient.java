package com.yih.auth.domain.oauth2;

import lombok.Data;

import java.util.List;

@Data
public class AppClient {
    private String clientName;
    private String description;

    private String clientId;
    private String secret;
    private List<String> scopes;
    private String redirectUris;
}
