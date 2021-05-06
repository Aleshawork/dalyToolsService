package com.dalyTools.dalyTools.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {

    @GetMapping("/admin")
    ResponseEntity<String> admin(){
        return   ResponseEntity.ok("Hello Admin!");
    }

    @GetMapping("/auth/all")
    ResponseEntity<String> all(){
        return ResponseEntity.ok("Hello any people!");
    }
}
