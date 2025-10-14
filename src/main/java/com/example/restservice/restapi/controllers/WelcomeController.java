package com.example.restservice.restapi.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class WelcomeController {

    @GetMapping("welcome")
    public String welcome() {
        return "Welcome to Spring Boot Training";
    }

    @GetMapping("greet")
    public String getData(String name) {
        return "Hello, " + name + "! Welcome to Spring Boot Training";
    }

}
