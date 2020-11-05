package com.yih.resource.ctl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
public class HelloCtl {

    @GetMapping("/hello")
    public Mono<String> getHello() {
        return Mono.just("Get hello");
    }

    @PostMapping("/hello")
    public String postHello() {
        return "Post hello";
    }
}