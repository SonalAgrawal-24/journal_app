package com.sonal.journalApp.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Disabled
@SpringBootTest
public class UserRepositoryImplTests {

    @Autowired
    UserRepositoryImpl userRepositoryImpl;

    @Test
    public void testSaveNewUser(){
        Assertions.assertNotNull(userRepositoryImpl.getUsersforSA());
    }
}
