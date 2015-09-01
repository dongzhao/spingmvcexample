package com.dzhao.springmvc.services;

import com.dzhao.springmvc.configuration.RepositoryConfiguration;
import com.dzhao.springmvc.configuration.ServiceConfiguration;
import com.dzhao.springmvc.model.User;
import com.dzhao.springmvc.repositories.UserRepository;
import com.dzhao.springmvc.services.api.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by dzhao on 25/08/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {ServiceConfiguration.class})
public class UserServiceTest {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }
    @Test
    public void testSave(){

        User kevin = new User();
        kevin.setUserName("Kevin");
        kevin.setPassword("kevin");
        kevin.setEmail("kevin@gmail.com");

        User newUser = userService.create(kevin);

        User result = userService.findById(newUser.getId());
        Assert.assertNotNull(result);
        Assert.assertEquals(newUser.getId(), result.getId());

    }

}
