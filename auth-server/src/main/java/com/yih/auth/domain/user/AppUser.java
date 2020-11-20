package com.yih.auth.domain.user;

import com.yih.auth.domain.oauth2.AppGrantedAuthority;
import com.yih.auth.entity.UserEntity;
import com.yih.auth.svc.UserService;
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
    private UserService userService;

    public AppUser() {

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

    public static AppUser findById(Long userId) {
        AppUser user = new AppUser();
        user = user.userService.findById(userId);
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
}
