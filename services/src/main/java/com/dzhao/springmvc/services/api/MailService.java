package com.dzhao.springmvc.services.api;

/**
 * Created by dzhao on 7/10/2015.
 */
public interface MailService {

    void sendEmail(String to, String subject, String body);

}
