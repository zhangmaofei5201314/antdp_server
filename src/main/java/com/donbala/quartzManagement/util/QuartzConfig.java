package com.donbala.quartzManagement.util;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * @author RedRush
 * @date 2020/8/31 15:36
 * @description:
 */
@Configuration
public class QuartzConfig {

    @Autowired
    QuartzJobFactory quartzJobFactory;

    @Bean
    public SchedulerFactoryBean scheduler() throws SchedulerException {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setJobFactory(quartzJobFactory);
        return factory;
    }

}
