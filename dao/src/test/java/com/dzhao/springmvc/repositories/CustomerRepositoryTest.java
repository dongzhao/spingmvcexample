package com.dzhao.springmvc.repositories;

import com.dzhao.springmvc.model.Customer;
import com.dzhao.springmvc.model.User;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by dzhao on 22/09/2015
 */
public class CustomerRepositoryTest extends AbstractRepositoryTest{

    @Test
    public void test_save_customer(){
        Customer domain = new Customer();
        domain.setFirstName("");
        domain.setLastName("");
        domain.setGender(true);

        customerRepository.save(domain);

        Customer result = customerRepository.findOne(domain.getId());
        Assert.assertNotNull(result);
    }
}
