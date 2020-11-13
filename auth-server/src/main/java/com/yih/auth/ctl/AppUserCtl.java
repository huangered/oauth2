package com.yih.auth.ctl;

import com.yih.auth.domain.user.RegisterUser;
import com.yih.auth.svc.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@Api(description = "the endpoint for user login")
@Slf4j
@RestController
public class AppUserCtl {
    @Autowired
    UserService userService;

    @ApiOperation("register new user")
    @PostMapping("/users")
    public ResponseEntity<Long> create(@RequestBody RegisterUser appUser) {
        return ResponseEntity.ok(userService.create(appUser));
    }

    @ApiOperation("check username occupied")
    @GetMapping("/username")
    public ResponseEntity<Boolean> isUserNameOccupied(@RequestParam String username) {
        return ResponseEntity.ok(userService.findByUsername(username).isPresent());
    }

    @ApiOperation("get user detail")
    @GetMapping("/users")
    public ResponseEntity<UserDetails> findUserByName(@RequestParam String username) {
        return ResponseEntity.ok(userService.findByUsername(username).get());
    }
}