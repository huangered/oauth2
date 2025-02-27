package com.yih.auth.util.oauth2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/*
the token store will save each jwt token to db
 */
@Slf4j
public class LynxJwtTokenStore extends JwtTokenStore {
    /**
     * Create a JwtTokenStore with this token enhancer (should be shared with the DefaultTokenServices if used).
     *
     * @param jwtTokenEnhancer
     */
    public LynxJwtTokenStore(JwtAccessTokenConverter jwtTokenEnhancer) {
        super(jwtTokenEnhancer);
    }

    @Override
    public void storeAccessToken(OAuth2AccessToken token, OAuth2Authentication authentication) {
        super.storeAccessToken(token, authentication);
        log.info("store token: {} for {}", token, authentication);
    }
}
