package com.dzhao.springmvc.services;

import com.dzhao.springmvc.services.api.AsyncMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

/**
 * Created by dzhao on 8/10/2015.
 */
@Service("asyncMailService")
public class AsyncMailServiceImpl implements AsyncMailService {

    private JavaMailSender mailSender;

    @Autowired
    public AsyncMailServiceImpl(JavaMailSender  mailSender){
        this.mailSender = mailSender;
    }

    @Async
    public Future<Boolean> sendEmail(String to, String subject, String body) throws InterruptedException{
        boolean result = false;
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        try {
            //mailSender.send(message);
            Thread.sleep(5000);
            result = true;
        }catch (Exception e){
            ;
        }
        return new AsyncResult<Boolean>(result);
    }
}
