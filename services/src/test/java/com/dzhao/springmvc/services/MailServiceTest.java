package com.dzhao.springmvc.services;

import junit.framework.Assert;
import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by dzhao on 7/10/2015.
 */
public class MailServiceTest extends AbstractServiceTest{
    @Test
    public void can_send_confirmation_email(){
        //mailService.sendEmail("dzhaoau@gmail.com", "FYI", "This is a test!");
    }

    @Test
    public void can_send_confirmation_email_async() throws ExecutionException, InterruptedException {
        Future<Boolean> result = asyncMailService.sendEmail("dzhaoau@gmail.com", "FYI", "This is a test!");
        Boolean status = null;
        System.out.println("finished...");
        while (!result.isDone()){
            System.out.println("#");
            Thread.sleep(1000);
        }
        status = result.get();
        Assert.assertTrue(status);
    }
}
