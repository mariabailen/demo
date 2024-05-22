package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class MainController {
    
    @GetMapping("/")
    public String main() {
        return "greetings to Maria from Spring";
    }
    
}
