package com.xpertgroup.demo.controllers;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/")
    public ResponseEntity<Map<String, String>> home() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Cat API Backend is running!");
        response.put("status", "OK");
        response.put("timestamp", java.time.LocalDateTime.now().toString());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/test")
    public ResponseEntity<Map<String, String>> test() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Test endpoint working!");
        response.put("status", "OK");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/health-simple")
    public ResponseEntity<Map<String, String>> healthSimple() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "UP");
        response.put("service", "Cat API Backend");
        return ResponseEntity.ok(response);
    }
}