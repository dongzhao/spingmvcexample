package com.dzhao.springmvc.repositories;

import com.dzhao.springmvc.configuration.RepositoryConfiguration;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by dzhao on 22/09/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {RepositoryConfiguration.class})
public abstract class AbstractRepositoryTest {

    protected UserProfileRepository userProfileRepository;
    protected UserRepository userRepository;
    protected CustomerRepository customerRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setUserProfileRepository(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    @Autowired
    public void setCustomerRepository(CustomerRepository customerRepository) {this.customerRepository = customerRepository;}
}
