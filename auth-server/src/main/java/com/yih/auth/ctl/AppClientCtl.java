package com.yih.auth.ctl;

import com.yih.auth.domain.AppClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppClientCtl {
    @PostMapping("/appclients")
    public ResponseEntity<Boolean> create(@RequestBody AppClient appClient) {
        return ResponseEntity.ok(Boolean.FALSE);
    }
}
