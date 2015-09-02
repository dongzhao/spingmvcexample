package com.dzhao.springmvc.services;

import com.dzhao.springmvc.configuration.ServiceConfiguration;
import com.dzhao.springmvc.model.User;
import com.dzhao.springmvc.model.UserProfile;
import com.dzhao.springmvc.services.api.UserProfileService;
import com.dzhao.springmvc.services.api.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * Created by dzhao on 25/08/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {ServiceConfiguration.class})
public class UserProfileServiceTest {
    private UserProfileService userProfileService;
    private UserService userService;

    @Autowired
    public void setUserProfileService(UserProfileService userProfileService){
        this.userProfileService = userProfileService;
    }

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

        UserProfile profile = new UserProfile();
        profile.setAddress("test");
        profile.setDateOfBirth(new Date());
        profile.setGender(true);
        profile.setUser(kevin);
        userProfileService.create(profile);

        UserProfile acturalProfile = userProfileService.findById(profile.getId());
        Assert.assertNotNull(acturalProfile);
        Assert.assertEquals(profile.getId(), acturalProfile.getId());

    }

}
