package com.yih.app.ctl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@Slf4j
@RestController
public class ThreadCtl {
    @GetMapping("/thread")
    public ResponseEntity<String> getHello() {
        MethodCoundRecorder.record();
        MethodCoundRecorder.print();
        return ResponseEntity.ok("ok");
    }
}