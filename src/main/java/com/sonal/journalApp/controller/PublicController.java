package com.sonal.journalApp.controller;

import com.sonal.journalApp.entity.User;
import com.sonal.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;

    @GetMapping("/health-check")
    public String HealthCheck(){
        return "Ok";
    }

    @GetMapping("create-user")
    @PostMapping
    public void createUSer(@RequestBody User user){
        userService.saveNewUser(user);
    }
}
