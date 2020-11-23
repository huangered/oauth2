package com.yih.auth.ctl;

import com.yih.auth.pojo.oauth2.AppClient;
import com.yih.auth.domain.user.AppUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(description = "the endpoint for Oauth2 client id", tags = "user")
@RestController
@RequestMapping("/api/v1")
public class AppClientCtl {
    @ApiOperation("create new Oauth2 client id")
    @PostMapping("/users/{userId}/appclients")
    public ResponseEntity<Boolean> create(@PathVariable Long userId,
                                          @RequestBody ClientRequest appClient) {
        AppUser.findById(userId).addClient(appClient);

        return ResponseEntity.ok(Boolean.TRUE);
    }

    @PatchMapping("/users/{userId}/appclients/{clientName}")
    public ResponseEntity<PatchResponse> patchSecret(@PathVariable Long userId,
                                                     @PathVariable String clientName) {
        AppClient client = AppUser.findById(userId).updateClientSecret(clientName);
        return ResponseEntity.ok(new PatchResponse(client.getClientId(), client.getClientSecret()));
    }

    @ApiOperation("remove new Oauth2 client id")
    @DeleteMapping("/users/{userId}/appclients")
    public ResponseEntity<Boolean> remove(@PathVariable Long userId,
                                          @RequestParam String clientName) {
        AppUser.findById(userId).removeClientDetails(clientName);
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
    public static class PatchResponse {
        private String clientSecret;
        private String clientId;

        public PatchResponse() {
        }

        public PatchResponse(String clientSecret, String clientId) {
            this.clientSecret = clientSecret;
            this.clientId = clientId;
        }
    }
}