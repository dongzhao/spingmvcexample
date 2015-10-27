package com.dzhao.springmvc.repositories;

import com.dzhao.springmvc.configuration.RepositoryConfiguration;
import com.dzhao.springmvc.model.Customer;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * Created by dzhao on 22/09/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {RepositoryConfiguration.class})
public abstract class AbstractRepositoryTest {

    @Autowired
    protected UserProfileRepository userProfileRepository;
    @Autowired
    protected UserRepository userRepository;
    @Autowired
    protected CustomerRepository customerRepository;

    @Before
    public void setUp(){
        initCustomers();
    }

    protected void clearAllCustomers(){
        customerRepository.deleteAll();
    }

    protected void initCustomers(){
        newCustomer("Tony", "Abbott", false);
        newCustomer("Malcolm", "Turnbull", false);
    }


    protected String newCustomer(String firstName, String lastName, Boolean gender){
        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setGender(gender);
        customer.setCreationDate(new Date());
        customerRepository.save(customer);
        return customer.getId();
    }
}
