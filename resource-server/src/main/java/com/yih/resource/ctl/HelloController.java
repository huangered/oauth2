package com.yih.resource.ctl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@Slf4j
@RestController
public class HelloController {
    @GetMapping("/hello")
    private String hello(OAuth2Authentication authentication, Principal principal) {
        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) authentication.getDetails();
        log.info("");
        log.info("detail {}", details.getDecodedDetails());
        return "hello";
    }
}
