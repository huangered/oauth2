package com.yih.auth.ctl;

import com.yih.auth.domain.user.AppUser;
import com.yih.auth.svc.LynxUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@Api(description = "the endpoint for user login", tags = "user")
@Slf4j
@RestController
@RequestMapping("/api/v1")
public class AppUserCtl {
    @Autowired
    LynxUserService userService;

    @ApiOperation("register new user")
    @PostMapping("/users")
    public ResponseEntity<Long> create(@RequestBody RegisterUser appUser) {
        AppUser client = new AppUser(appUser.getUsername(), appUser.getPassword());

        return ResponseEntity.ok(client.create());
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

    @ApiOperation("update user password")
    @PutMapping("/users/{userId}/password")
    public ResponseEntity<String> updatePassword(@PathVariable Long userId,
                                                 @RequestParam String password) {
        AppUser user = AppUser.findById(userId);
        user.updatePassword(password);
        return ResponseEntity.ok("ok");
    }


    @Data
    public static class RegisterUser {
        private String username;
        private String password;
    }
}