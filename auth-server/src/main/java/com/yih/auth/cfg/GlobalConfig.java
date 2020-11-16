package com.yih.auth.cfg;

import com.yih.auth.util.oauth2.TrackJwtTokenStore;
import lombok.val;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

@Configuration
public class GlobalConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public TokenStore tokenStore() {
        TrackJwtTokenStore ts = new TrackJwtTokenStore(jwtAccessTokenConverter());
        return ts;
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        val converter = new JwtAccessTokenConverter();
        converter.setSigningKey("test");
        return converter;
    }
}
