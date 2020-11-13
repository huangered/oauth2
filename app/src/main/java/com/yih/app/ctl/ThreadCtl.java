package com.yih.app.ctl;

import com.yih.app.util.MethodCoundRecorder;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@Slf4j
@RestController
@Api("hello world")
public class ThreadCtl {
    @GetMapping("/thread")
    public ResponseEntity<String> getHello() {
        MethodCoundRecorder.record("thread");
        MethodCoundRecorder.print();
        return ResponseEntity.ok("ok");
    }
}