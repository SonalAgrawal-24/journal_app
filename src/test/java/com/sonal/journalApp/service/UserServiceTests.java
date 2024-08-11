package com.sonal.journalApp.service;

import com.sonal.journalApp.UserArgumentProvider;
import com.sonal.journalApp.entity.User;
import com.sonal.journalApp.repository.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class UserServiceTests {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @ParameterizedTest
//    @CsvSource({
//            "ram",
//            "shyam",
//            "sonal"
//    })
//    @ValueSource(strings = {
//            "ram",
//            "shyam",
//            "sonal"
//    })
    @ArgumentsSource(UserArgumentProvider.class)
    public void testFindByUserName(User user){
//       assertNotNull(userRepository.findByUserName(user));
        assertTrue(userService.saveNewUser(user));
    }

    @Disabled
    @ParameterizedTest
    @CsvSource({
            "1,1,2",
            "2,10,12",
            "3,3,9"
    })
    public  void test(int a , int b, int expected){
        assertEquals(expected, a+b);
    }
}
