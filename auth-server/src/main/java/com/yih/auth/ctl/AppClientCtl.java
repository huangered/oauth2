package com.yih.auth.ctl;

import com.google.common.collect.Sets;
import com.yih.auth.domain.oauth2.AppClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientRegistrationService;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.UUID;

@Api(description = "the endpoint for Oauth2 client id", tags = "user")
@RestController
public class AppClientCtl {
    @Autowired
    ClientRegistrationService service;
@Autowired
    PasswordEncoder encoder;
    @ApiOperation("create new Oauth2 client id")
    @PostMapping("/api/v1/appclients")
    public ResponseEntity<Boolean> create(@RequestBody ClientRequest appClient) {
        AppClient client = new AppClient();
        client.setClientName(appClient.getName());
        client.setRedirectUri(appClient.getUrl());
//        client.setClientId(UUID.randomUUID().toString());
//        client.setClientSecret(UUID.randomUUID().toString());
        client.setClientId("test");
        client.setClientSecret(encoder.encode("test"));
        client.setScope(Sets.newHashSet("read"));
        client.setDescription("test");
        client.setAuthorizedGrantTypes(Sets.newHashSet("authorization_code","password","refresh_token"));
        service.addClientDetails(client);
        return ResponseEntity.ok(Boolean.FALSE);
    }

    @ApiOperation("remove new Oauth2 client id")
    @DeleteMapping("/api/v1/appclients")
    public ResponseEntity<Boolean> remove(@RequestParam String clientName) {

        return ResponseEntity.ok(Boolean.FALSE);
    }

    @Data
    public static class ClientRequest {
        private String name;
        private String url;

        public ClientRequest(){

        }
    }
}