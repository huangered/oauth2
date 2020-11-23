package com.yih.auth.cfg;

import com.yih.auth.util.oauth2.LynxJwtTokenStore;
import lombok.val;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

/**
 * JWT token 和 Encoder 相关 bean
 */
@Configuration
public class LynxSecretConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public TokenStore tokenStore() {
        LynxJwtTokenStore ts = new LynxJwtTokenStore(jwtAccessTokenConverter());
        return ts;
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        val converter = new JwtAccessTokenConverter();
        converter.setSigningKey("test");
        return converter;
    }

    public static void main(String[] argc){
        BCryptPasswordEncoder bpe = new BCryptPasswordEncoder();
        System.out.println(bpe.encode("12345678"));
    }
}
