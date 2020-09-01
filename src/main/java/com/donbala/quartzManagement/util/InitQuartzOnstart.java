package com.donbala.quartzManagement.util;

import com.donbala.quartzManagement.service.QuartzServiceIntf;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @author RedRush
 * @date 2020/8/31 15:38
 * @description:
 */
@Service("InitQuartzOnstart")
public class InitQuartzOnstart {

    @Autowired
    QuartzServiceIntf quartzService;

    @PostConstruct
    public void startQuartz() throws SchedulerException {
        System.out.println("****InitJob");
        quartzService.initJobsOnstart();
    }
}
