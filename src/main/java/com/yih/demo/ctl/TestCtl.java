package com.yih.demo.ctl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
public class TestCtl {

    @Secured(value = {"ROLE_READ","ROLE_WRITE"})
    @GetMapping("/products")
    public List<String> all(){
        SecurityContext sc = SecurityContextHolder.getContext();
        Authentication a = sc.getAuthentication();
        log.info("{}", a.getName());
        List<String> data = new ArrayList<>();
        data.add("a0");
        data.add("a1");
        data.add("a2");
        data.add("a3");
        return data;
    }
}
