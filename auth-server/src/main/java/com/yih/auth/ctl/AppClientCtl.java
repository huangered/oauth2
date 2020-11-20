package com.yih.auth.ctl;

import com.google.common.collect.Sets;
import com.yih.auth.domain.oauth2.AppClient;
import com.yih.auth.svc.LynxClientRegistrationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@Api(description = "the endpoint for Oauth2 client id", tags = "user")
@RestController
@RequestMapping("/api/v1")
public class AppClientCtl {
    @Autowired
    LynxClientRegistrationService service;

    @Autowired
    PasswordEncoder encoder;

    @ApiOperation("create new Oauth2 client id")
    @PostMapping("/users/{userId}/appclients")
    public ResponseEntity<Boolean> create(@PathVariable Long userId,
                                          @RequestBody ClientRequest appClient) {
        AppClient client = new AppClient();
        client.setClientName(appClient.getName());
        client.setRedirectUri(appClient.getUrl());
//        client.setClientId(UUID.randomUUID().toString());
//        client.setClientSecret(UUID.randomUUID().toString());
        client.setClientId("test");
        client.setClientSecret(encoder.encode("test"));
        client.setScope(Sets.newHashSet("read"));
        client.setDescription("test");
        client.setAuthorizedGrantTypes(Sets.newHashSet("authorization_code", "password", "refresh_token"));
        service.addClientDetails(userId, client);
        return ResponseEntity.ok(Boolean.FALSE);
    }

    @PatchMapping("/users/{userId}/appclients")
    public ResponseEntity<AppClient> patchSecret(@PathVariable Long userId,
                                                 @RequestParam PatchRequest patchRequest) {
        AppClient client = service.updateClientSecret(userId, patchRequest.getClientId(), patchRequest.getClientSecret());
        return ResponseEntity.ok(client);
    }

    @ApiOperation("remove new Oauth2 client id")
    @DeleteMapping("/users/{userId}/appclients")
    public ResponseEntity<Boolean> remove(@PathVariable Long userId,
                                          @RequestParam String clientId) {
        service.removeClientDetails(userId, clientId);
        return ResponseEntity.ok(Boolean.FALSE);
    }

    @Data
    public static class ClientRequest {
        private Long userId;
        private String name;
        private String url;

        public ClientRequest() {

        }
    }

    @Data
    public static class PatchRequest {
        private String clientSecret;
        private String clientId;

        public PatchRequest() {

        }
    }
}