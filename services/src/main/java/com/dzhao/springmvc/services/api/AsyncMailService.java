package com.dzhao.springmvc.services.api;

import java.util.concurrent.Future;

/**
 * Created by dzhao on 7/10/2015.
 */
public interface AsyncMailService {

    Future<Boolean> sendEmail(String to, String subject, String body) throws InterruptedException;

}
