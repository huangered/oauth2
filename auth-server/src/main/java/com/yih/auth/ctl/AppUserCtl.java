package com.yih.auth.ctl;

import com.yih.auth.domain.AppGrantedAuthority;
import com.yih.auth.domain.AppScope;
import com.yih.auth.domain.AppUser;
import com.yih.auth.svc.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@Slf4j
@RestController
public class AppUserCtl {
    @Autowired
    UserService userService;

    @PostMapping("/users")
    public ResponseEntity<Boolean> create(@RequestBody AppUser appUser) {
        log.info("create user {}", appUser);
        appUser.setAuthorities(new ArrayList<>());
        appUser.getAuthorities().add(new AppGrantedAuthority(AppScope.User.name()));
        return ResponseEntity.ok(userService.create(appUser));
    }
}