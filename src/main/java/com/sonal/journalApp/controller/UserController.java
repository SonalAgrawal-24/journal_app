package com.sonal.journalApp.controller;

import com.sonal.journalApp.api.Response.WeatherResponse;
import com.sonal.journalApp.entity.User;
import com.sonal.journalApp.repository.UserRepository;
import com.sonal.journalApp.service.UserService;
import com.sonal.journalApp.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WeatherService weatherService;

    @PostMapping
    public void createUSer(@RequestBody User user) {
        userService.saveNewUser(user);
    }

    @PutMapping
    public ResponseEntity<?> updateUSer(@RequestBody User user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User userInDb = userService.findByUSerName(userName);
            userInDb.setUserName(user.getUserName());
            userInDb.setPassword(user.getPassword());
            userService.saveNewUser(userInDb);
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUserById(@RequestBody User user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userRepository.deleteByUserName(authentication.getName());
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<?> greeting(@RequestBody User user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        WeatherResponse weatherResponse = weatherService.getWeather("Mumbai");
        String greeting = "";
        if(weatherResponse != null)
            greeting = " Weather feels like " + weatherResponse.getCurrent().getFeelslike();

        return  new ResponseEntity<>("hi " + authentication.getName() + greeting , HttpStatus.OK);
    }
}
