package com.dzhao.springmvc.services;

import com.dzhao.springmvc.services.api.AsyncMailService;
import com.dzhao.springmvc.services.api.MailService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by dzhao on 8/10/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {TestConfiguration.class})
public abstract class AbstractServiceTest {

    @Autowired
    protected MailService mailService;

    @Autowired
    protected AsyncMailService asyncMailService;

/*    @Autowired
    public void setMailService(MailService mailService){this.mailService = mailService;}*/
}
