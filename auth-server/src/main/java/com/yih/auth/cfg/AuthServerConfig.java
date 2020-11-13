package com.yih.auth.cfg;

import com.yih.auth.util.oauth2.LynxTokenEnhancer;
import com.yih.auth.util.oauth2.TrackJwtTokenStore;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableAuthorizationServer
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    @Qualifier("JpaClientDetailsService")
    ClientDetailsService jpaClientDetailService;
    @Autowired
    private AuthenticationManager authenticationManager;
    private String jwtKey = "test";

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.authenticationManager(authenticationManager);
        // for jwt
        TokenEnhancerChain chain = new TokenEnhancerChain();
        List<TokenEnhancer> enhancerList = new ArrayList<>();
        enhancerList.add(new LynxTokenEnhancer());
        enhancerList.add(jwtAccessTokenConverter());
        chain.setTokenEnhancers(enhancerList);
        endpoints.tokenStore(tokenStore())
                .tokenEnhancer(chain);
        endpoints.pathMapping("/oauth/confirm_access", "/custom/confirm_access");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        String appClientId = "client";
//        String appSecret = encoder.encode("secret");
//        clients.inMemory()
//                .withClient(appClientId)
//                .secret(appSecret)
//                //.authorizedGrantTypes("password", "refresh_token")
//                .authorizedGrantTypes("authorization_code", "password", "refresh_token")
//                .scopes("read", "write")
//                .redirectUris("http://localhost:8080/hello")
//                .and()
//                /*
//                给resource server访问/oauth/check_token
//                 */
//                .withClient("resourceserver")
//                .secret("resourceserversecret");

        clients.withClientDetails(jpaClientDetailService);
    }

    /*
     * 设置访问check_token方法的条件
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.checkTokenAccess("isAuthenticated()");
    }

    @Bean
    public TokenStore tokenStore() {
        TrackJwtTokenStore ts = new TrackJwtTokenStore(jwtAccessTokenConverter());
        return ts;
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        val converter = new JwtAccessTokenConverter();
        converter.setSigningKey(jwtKey);
        return converter;
    }
}