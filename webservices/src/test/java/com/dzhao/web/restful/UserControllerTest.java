/*
package com.dzhao.web.restful;

import com.dzhao.core.dao.repository.UserRepository;
import com.dzhao.core.dao.service.api.UserService;
import com.dzhao.core.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Arrays;

*/
/**
 * Created by dzhao on 20/08/2015.
 *//*

@RunWith(MockitoJUnitRunner.class)
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
        kevin = new User("Kevin", "kevin", "kevin@gmail.com");
        tom = new User("Tom", "tom", "tom@gmail.com");
        jerry = new User("Jerry", "jerry", "jerry@gmail.com");

        userRepository.deleteAll();
        userRepository.save(Arrays.asList(kevin, tom, jerry));

        RestAssured.port = port;
    }

    @Test
    public void shouldCreateUser() throws Exception {
        final User savedUser = stubServiceToReturnStoredUser();
        final User user = new User();
        User returnedUser = userController.createUser(user);
        // verify user was passed to UserService
        verify(userService, times(1)).save(user);
        assertEquals("Returned user should come from the service", savedUser, returnedUser);
    }

    private User stubServiceToReturnStoredUser() {
        final User user = new User();
        when(userService.save(any(User.class))).thenReturn(user);
        return user;
    }
}
*/
