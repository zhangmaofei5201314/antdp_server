package com.donbala.quartzManagement.controller;

import ch.qos.logback.classic.Logger;
import com.donbala.loginManagement.controller.LoginController;
import com.donbala.quartzManagement.model.JobRunLog;
import com.donbala.quartzManagement.model.Quartz;
import com.donbala.quartzManagement.service.QuartzServiceIntf;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: RedRush
 * @Date:   2020/8/25 9:57
 */

@RestController
public class JobplanController {

    public final static Logger log = (Logger) LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private QuartzServiceIntf quartzServiceIntf;

    /**
     * @Author: RedRush
     * @Date:   2020/8/25 16:42
     * @description: 获取作业列表
     */
    @RequestMapping(value = "/controller/quartz/management/queryjobplanlist", method = RequestMethod.GET)
    public List<Quartz> getJobPlanList(){
        return quartzServiceIntf.getJobPlanList();
    }

    @RequestMapping(value = "/controller/quartz/management/queryjobrunlog", method = RequestMethod.GET)
    public List<JobRunLog> getJobRunLogList(){
        return quartzServiceIntf.getJobRunLogList();
    }

    /**
     * @Author: RedRush
     * @Date:   2020/8/25 16:50
     * @description: 获取作业参数
     */
    @RequestMapping(value="/controller/quartz/management/getJobParamList",method= RequestMethod.POST)
    public List<Quartz> getJobParamList(String jobCode){
        List<Quartz> list = quartzServiceIntf.getJobParamList(jobCode);
        return list;
    }

}
