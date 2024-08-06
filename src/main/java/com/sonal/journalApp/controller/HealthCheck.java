package com.sonal.journalApp.controller;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheck {
//    @GetMapping("/health-check")
    public String HealthCheck(){
        return "Ok";
    }
}
