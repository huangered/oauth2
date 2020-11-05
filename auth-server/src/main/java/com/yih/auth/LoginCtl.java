package com.yih.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
public class LoginCtl {
    @GetMapping("/")
    public String main() {
        return "grant.html";
    }
}
