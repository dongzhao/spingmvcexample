/*
package com.dzhao.app.web.config;

import javax.servlet.Registration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

*/
/**
 * Created by dzhao on 19/08/2015.
 *//*

public class Initializer implements WebApplicationInitializer {

    private static final String DISPATCHER_SERVLET_NAME = "dispatcher";

    public void onStartup(ServletContext servletContext)
            throws ServletException {
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(WebAppConfig.class);
        servletContext.addListener(new ContextLoaderListener(ctx));

        ctx.setServletContext(servletContext);

        Registration.Dynamic servlet = servletContext.addServlet(DISPATCHER_SERVLET_NAME,
                new DispatcherServlet(ctx));


        servlet.addMapping("/");
        servlet.setLoadOnStartup(1);
    }

}
*/
