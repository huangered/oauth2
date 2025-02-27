package com.yih.auth.cfg;

import com.yih.auth.util.oauth2.LynxTokenEnhancer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
public class LynxAuthServerConfig extends AuthorizationServerConfigurerAdapter {
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    @Qualifier("JpaClientDetailsService")
    private ClientDetailsService jpaClientDetailService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenStore tokenStore;
    @Autowired
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.authenticationManager(authenticationManager);
        // 自定义 jwt
        TokenEnhancerChain chain = new TokenEnhancerChain();
        List<TokenEnhancer> enhancerList = new ArrayList<>();
        enhancerList.add(new LynxTokenEnhancer());
        enhancerList.add(jwtAccessTokenConverter);
        chain.setTokenEnhancers(enhancerList);
        endpoints.tokenStore(tokenStore)
                .tokenEnhancer(chain);
        // 自定义approve界面
        endpoints.pathMapping("/oauth/confirm_access", "/custom/confirm_access");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // 使用 jpa 数据库读取client信息
        clients.withClientDetails(jpaClientDetailService);
    }

    /*
     * 设置访问check_token方法的条件
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.checkTokenAccess("isAuthenticated()");
    }
}