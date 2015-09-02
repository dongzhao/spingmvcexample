/*
package com.dzhao.springmvc.controllers;

import com.dzhao.springmvc.Application;
import com.dzhao.springmvc.model.User;
import com.dzhao.springmvc.repositories.UserRepository;
import com.dzhao.springmvc.services.api.UserService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Arrays;
import java.util.Date;

@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class UserControllerTest {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    User kevin;
    User tom;
    User jerry;

    @Value("${local.server.port}")
    int port;

    @Before
    public void setUp() {
        kevin = createUser("Kevin", "kevin", "kevin@gmail.com");
        tom = createUser("Tom", "tom", "tom@gmail.com");
        jerry = createUser("Jerry", "jerry", "jerry@gmail.com");

        userRepository.deleteAll();
        userRepository.save(Arrays.asList(kevin, tom, jerry));
    }

    private User createUser(String username, String password, String email){
        User newUser = new User();
        newUser.setUserName(username);
        newUser.setPassword(password);
        newUser.setEmail(email);
        newUser.setCreationDate(new Date());
        newUser.setLastModificationDate(new Date());
        return newUser;
    }

    @Test
    public void shouldCreateUser() throws Exception {
*/
/*        final User savedUser = stubServiceToReturnStoredUser();
        final User user = new User();
        User returnedUser = userController.createUser(user);
        // verify user was passed to UserService
        verify(userService, times(1)).save(user);
        assertEquals("Returned user should come from the services", savedUser, returnedUser);*//*

    }

}
*/
