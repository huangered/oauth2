package com.yih.demo.cfg;

import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.csrf.CsrfFilter;

@EnableGlobalMethodSecurity(securedEnabled = true)
@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public UserDetailsService userDetailsService(){
        var userDetailsService = new InMemoryUserDetailsManager();
        var user = User.withUsername("peter").password("peter").authorities("ROLE_WRITE").build();

        userDetailsService.createUser(user);
        return userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic(c -> {
            c.realmName("test");
            c.authenticationEntryPoint(new CustomEntryPoint());
        });
        http.addFilterAfter(new CsrfTokenLogger(), CsrfFilter.class)
                .authorizeRequests().anyRequest().authenticated();
    }

    @Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(customAuthenticationProvider);
    }
}
