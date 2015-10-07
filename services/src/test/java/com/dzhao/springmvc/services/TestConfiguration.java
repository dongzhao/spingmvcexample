package com.dzhao.springmvc.services;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by dzhao on 7/10/2015.
 */
@Configuration
//@EnableAutoConfiguration
@ComponentScan(basePackageClasses = {MailServiceImpl.class})
public class TestConfiguration {
}
