package com.dzhao.springmvc.repositories;

import com.dzhao.springmvc.configuration.RepositoryConfiguration;
import com.dzhao.springmvc.model.User;
import com.dzhao.springmvc.model.UserProfile;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * Created by dzhao on 24/08/2015.
 */
public class UserProfileRepositoryTest extends AbstractRepositoryTest{
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
        userProfileRepository.saveAndFlush(profile);
        userProfileRepository.flush();

        UserProfile acturalProfile = userProfileRepository.findOne(profile.getId());
        Assert.assertNotNull(acturalProfile);
        Assert.assertEquals(profile.getId(), acturalProfile.getId());
    }
}
