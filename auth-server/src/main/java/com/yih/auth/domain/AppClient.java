package com.yih.auth.domain;

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
