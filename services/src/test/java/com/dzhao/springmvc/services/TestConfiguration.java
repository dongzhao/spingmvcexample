package com.dzhao.springmvc.services;

/*import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;*/

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Created by dzhao on 7/10/2015.
 */
@Configuration
@EnableAutoConfiguration
@EnableAsync
@ComponentScan(basePackages = {"com.dzhao.springmvc"})
public class TestConfiguration {
}
