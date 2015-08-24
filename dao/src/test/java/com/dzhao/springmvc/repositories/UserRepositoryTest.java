package com.dzhao.springmvc.repositories;

import com.dzhao.springmvc.configuration.RepositoryConfiguration;
import com.dzhao.springmvc.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by dzhao on 24/08/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {RepositoryConfiguration.class})
public class UserRepositoryTest {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Test
    public void testSave(){

        User kevin = new User();
        kevin.setUserName("Kevin");
        kevin.setPassword("kevin");
        kevin.setEmail("kevin@gmail.com");

        userRepository.save(kevin);
        userRepository.flush();

        User result = userRepository.findOne(kevin.getId());
        Assert.assertNotNull(result);
        Assert.assertEquals(kevin.getId(), result.getId());

    }
}
