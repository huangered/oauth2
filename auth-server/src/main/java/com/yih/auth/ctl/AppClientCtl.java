package com.yih.auth.ctl;

import com.yih.auth.domain.oauth2.AppClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.ClientRegistrationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "the endpoint for Oauth2 client id")
@RestController
public class AppClientCtl {
    @Autowired
    ClientRegistrationService service;

    @ApiOperation("create new Oauth2 client id")
    @PostMapping("/appclients")
    public ResponseEntity<Boolean> create(@RequestBody AppClient appClient) {
        service.addClientDetails(null);
        return ResponseEntity.ok(Boolean.FALSE);
    }
}
