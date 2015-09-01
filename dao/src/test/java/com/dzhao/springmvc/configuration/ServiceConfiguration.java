package com.dzhao.springmvc.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by dzhao on 25/08/2015.
 */
@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.dzhao.springmvc.model"})
@EnableJpaRepositories(basePackages = {"com.dzhao.springmvc.repositories"})
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.dzhao.springmvc.services"})
public class ServiceConfiguration {
}
