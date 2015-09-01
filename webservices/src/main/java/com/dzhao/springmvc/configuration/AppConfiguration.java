package com.dzhao.springmvc.configuration;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by dzhao on 25/08/2015.
 */
@Configuration
@EnableConfigurationProperties(ConnectionSettings.class)
public class AppConfiguration {
}
