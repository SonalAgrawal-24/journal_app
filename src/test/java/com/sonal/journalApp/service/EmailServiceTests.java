package com.sonal.journalApp.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTests {

    @Autowired
    EmailService emailService;

    @Test
    void testSendMail(){
        emailService.sendMail("agrawalsonal0624@gmail.com",
                "Testing Java Mail Sender",
                "Hi, testgin without authentication?");
    }
}
