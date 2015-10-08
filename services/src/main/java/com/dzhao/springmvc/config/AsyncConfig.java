package com.dzhao.springmvc.config;

import org.apache.log4j.LogManager;
import org.slf4j.Logger;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.aop.interceptor.SimpleAsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.Executor;

/**
 * Created by dzhao on 8/10/2015.
 */
@Configuration
@EnableAsync(proxyTargetClass = true)
@EnableScheduling
public class AsyncConfig implements SchedulingConfigurer, AsyncConfigurer {
    //private static final Logger log = LogManager.getLogger();
   // private static final Logger schedulingLogger = LogManager.getLogger(log.getName() + ".[scheduling]");

    @Bean
    public ThreadPoolTaskScheduler taskScheduler() {

        //log.info("Setting up thread pool task scheduler with 20 threads.");
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(2);
        scheduler.setThreadNamePrefix("task-");
        scheduler.setAwaitTerminationSeconds(120);  // 2 minutes
        scheduler.setWaitForTasksToCompleteOnShutdown(true);
        //scheduler.setErrorHandler(t -> schedulingLogger.error("Unknown error occurred while executing task.", t));
        //scheduler.setRejectedExecutionHandler((r, e) -> schedulingLogger.error("Execution of task {} was rejected for unknown reasons.", r));
        return scheduler;
    }


    public Executor getAsyncExecutor() {
        Executor executor = this.taskScheduler();
        //log.info("Configuring asynchronous method executor {}.", executor);
        return executor;
    }

    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new SimpleAsyncUncaughtExceptionHandler();
    }

    public void configureTasks(ScheduledTaskRegistrar registrar) {
        TaskScheduler scheduler = this.taskScheduler();
        //log.info("Configuring scheduled method executor {}.", scheduler);
        registrar.setTaskScheduler(scheduler);
    }
}
