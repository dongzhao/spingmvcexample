package com.dzhao.springmvc.controllers;

import com.dzhao.springmvc.model.Customer;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * Created by dzhao on 21/10/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RestControllerTestConfiguration.class)
public class CustomerRestControllerTest extends AbstractRestControllerTest{

    private final String get_all_url = "/rest/api/Customer";
    private final String post_save_url = "/rest/api/Customer/";

    @Test
    public void test_get_all(){
        clearAllCustomers();
        initCustomers();

        Customer[] customer = getEntityList(get_all_url, Customer[].class);
        Assert.assertNotNull(customer);
        Assert.assertTrue(customer.length == 2);
    }

    @Test
    public void test_create_customer(){
        clearAllCustomers();

        Customer domain = new Customer();
        domain.setFirstName("test1");
        domain.setLastName("test2");
        domain.setGender(false);
        domain.setCreationDate(new Date());

        String postJson = gson.toJson(domain);
        Customer customer = postEntity(post_save_url, postJson, Customer.class);
        Assert.assertNotNull(customer);
    }
}
