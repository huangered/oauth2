package com.yih.auth.domain.user;

import com.google.common.collect.Sets;
import com.yih.auth.ctl.AppClientCtl;
import com.yih.auth.pojo.oauth2.AppClient;
import com.yih.auth.pojo.oauth2.AppGrantedAuthority;
import com.yih.auth.entity.UserEntity;
import com.yih.auth.svc.LynxOauth2ClientService;
import com.yih.auth.svc.LynxUserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.stream.Collectors;

@Configurable
@Data
public class AppUser implements UserDetails {

    private Long id;
    private String username;
    private String password;
    private boolean enabled;
    private boolean credentialsNonExpired;
    private boolean accountNonLocked;
    private boolean accountNonExpired;
    private String telephone;
    private List<AppGrantedAuthority> authorities;

    @Autowired
    private LynxUserService userService;

    @Autowired
    private LynxOauth2ClientService clientService;

    public AppUser() {

    }

    private AppUser(Long id) {
        this.id = id;
    }

    public AppUser(UserEntity userEntity, List<String> authorities) {
        this.id = userEntity.getId();
        this.username = userEntity.getUsername();
        this.password = userEntity.getPassword();
        this.enabled = userEntity.isEnabled();
        this.credentialsNonExpired = userEntity.isCredentialsNonExpired();
        this.accountNonExpired = userEntity.isAccountNonExpired();
        this.accountNonLocked = userEntity.isAccountNonLocked();
        this.authorities = authorities.stream().map(AppGrantedAuthority::new).collect(Collectors.toList());
    }

    public AppUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Load user from db
     */
    public static AppUser findById(Long userId) {
        AppUser user = new AppUser();
        user = user.userService.findById(userId);
        return user;
    }

    /**
     * build user by user id
     */
    public static AppUser buildById(Long userId){
        AppUser user = new AppUser(userId);
        return user;
    }

    public long create() {
        id = userService.create(username, password);
        return id;
    }

    public void updatePassword(String password) {
        this.password = password;
        userService.updatePassword(password);
    }

    public void remove(){
        userService.remove(id);
    }

    public void addClient(AppClientCtl.ClientRequest appClient) {
        AppClient client = new AppClient();
        client.setClientName(appClient.getName());
        client.setRedirectUri(appClient.getUrl());
        client.setClientId(null);
        client.setClientSecret(null);
        client.setScope(Sets.newHashSet("read"));
        client.setDescription("test");
        client.setAuthorizedGrantTypes(Sets.newHashSet("authorization_code", "password", "refresh_token"));
        clientService.addClientDetails(id, client);
    }

    public AppClient updateClientSecret(String clientName) {
        return clientService.updateClientSecret(id, clientName);
    }

    public void removeClientDetails(String clientName) {
        clientService.removeClientDetails(id, clientName);
    }
}
