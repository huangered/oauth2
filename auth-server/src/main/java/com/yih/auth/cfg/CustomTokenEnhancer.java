package com.yih.auth.cfg;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class CustomTokenEnhancer implements TokenEnhancer {
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        val token = new DefaultOAuth2AccessToken(accessToken);
        Map<String, Object> info = new HashMap<>();
        info.put("a", "a2");
        info.put("b", "c");
        log.info("expire {}", accessToken.getExpiration());
        log.info("expire {}", accessToken.getExpiresIn());
        token.setAdditionalInformation(info);
        return token;
    }
}
