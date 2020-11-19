package com.yih.auth.util.oauth2;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class LynxTokenEnhancer implements TokenEnhancer {
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        DefaultOAuth2AccessToken token = new DefaultOAuth2AccessToken(accessToken);
        Map<String, Object> info = new HashMap<>();
        info.put("application-code", "lynx");
        token.setAdditionalInformation(info);
        return token;
    }
}
