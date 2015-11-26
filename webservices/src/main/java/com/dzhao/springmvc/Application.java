package com.dzhao.springmvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by dzhao on 20/08/2015.
 */
@SpringBootApplication
public class Application {

    public static void main(final String[] args) {
        SpringApplication app = new SpringApplication(Application.class);
        app.run(Application.class, args);
        app.setShowBanner(true);
    }
}
