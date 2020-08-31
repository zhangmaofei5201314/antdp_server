package com.donbala.quartzManagement.util;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.scheduling.quartz.AdaptableJobFactory;
import org.springframework.stereotype.Component;

/**
 * @author RedRush
 * @date 2020/8/31 15:34
 * @description:
 */
@Component
public class QuartzJobFactory extends AdaptableJobFactory {
    @Autowired
    private AutowireCapableBeanFactory autowireCapableBeanFactory;

    @Override
    protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
        Object jobInstance = super.createJobInstance(bundle);//创建Job实例
        autowireCapableBeanFactory.autowireBean(jobInstance);//Ioc容器向job实例中通过autowire注解注入bean
        return jobInstance;
    }
}
