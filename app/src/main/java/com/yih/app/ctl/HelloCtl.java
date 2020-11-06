package com.yih.app.ctl;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@Slf4j
@RestController
public class HelloCtl {

    @GetMapping("/hello")
    public String getHello(@RequestParam String code, @RequestParam String state) {
        log.info("code {}, state {}", code, state);
        String appClientId = "client";
        String appSecret = "secret";

        HttpResponse<JsonNode> dd =   Unirest.post("http://localhost:8081/oauth/token")
                .basicAuth(appClientId, appSecret)
                .field("code",code)
                .field("grant_type", "authorization_code")
                .field("scope", "read").asJson();

        return dd.getBody().toPrettyString();
    }
}