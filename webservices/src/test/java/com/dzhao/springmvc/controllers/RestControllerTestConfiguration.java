package com.dzhao.springmvc.controllers;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by dzhao on 24/08/2015.
 */
@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.dzhao.springmvc.model"})
@EnableJpaRepositories(basePackages = {"com.dzhao.springmvc.repositories"})
@EnableTransactionManagement
@EnableAsync
@ComponentScan(basePackages = {"com.dzhao.springmvc"})
public class RestControllerTestConfiguration {

}
