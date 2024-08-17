package com.sonal.journalApp;

import com.sonal.journalApp.entity.User;
import com.sonal.journalApp.repository.UserRepository;
import com.sonal.journalApp.service.UserDetailServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;

import static org.mockito.Mockito.when;

@Disabled
//@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UserDetailsServiceImplTests {

//    @Autowired
    @InjectMocks
    private UserDetailServiceImpl userDetailServiceImpl;

//    @MockBean
    @Mock
    private UserRepository userRepository;

    @Test
    void loadUSerByUsernameTest(){
        when(userRepository.findByUserName(ArgumentMatchers.anyString())).thenReturn(User.builder().userName("ram").password("wifulb").roles(new ArrayList<>()).build());
        UserDetails user = userDetailServiceImpl.loadUserByUsername("ram");
        Assertions.assertNotNull(user);
    }
}
