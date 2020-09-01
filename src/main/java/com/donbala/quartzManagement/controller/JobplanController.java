package com.donbala.quartzManagement.controller;

import ch.qos.logback.classic.Logger;
import com.donbala.loginManagement.controller.LoginController;
import com.donbala.quartzManagement.model.Quartz;
import com.donbala.quartzManagement.service.QuartzServiceIntf;
import com.donbala.userManagement.service.CmsUserServiceIntf;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * @Author: RedRush
 * @Date:   2020/8/25 9:57
 * @description:
 */

@RestController
public class JobplanController {

    public final static Logger log = (Logger) LoggerFactory.getLogger(JobplanController.class);

    @Autowired
    private QuartzServiceIntf quartzServiceIntf;

    @Autowired
    private CmsUserServiceIntf cmsUserServiceIntf;

    /**
     * @Author: RedRush
     * @Date:   2020/8/25 16:42
     * @description: 获取作业列表
     */
    @RequestMapping(value = "/controller/quartz/management/queryjobplanlist", method = RequestMethod.GET)
    public List<Quartz> getJobPlanList(){
        return quartzServiceIntf.getJobPlanList();
    }

    /**
     * @Author: RedRush
     * @Date:   2020/8/25 16:50
     * @description: 获取作业参数
     */
    @RequestMapping(value="/controller/quartz/management/getJobParamList",method= RequestMethod.POST)
    public List<Quartz> getJobParamList(String jobCode){
        return quartzServiceIntf.getJobParamList(jobCode);
    }

    /**
     * @Author: RedRush
     * @Date:   2020/8/26 16:38
     * @description: 新建任务
     */
    @RequestMapping(value = "/controller/quartz/management/insertJob", method = RequestMethod.POST)
    public Map<String, Object> insertJobPlan(Quartz quartz){
        String userName = cmsUserServiceIntf.getUserByToken(quartz.getToken()).getName();
        quartz.setMakeUser(userName);
        quartz.setModifyUser(userName);
        return quartzServiceIntf.insertJobAndParam(quartz);
    }

    /**
     * @Author: RedRush
     * @Date:   2020/8/26 18:16
     * @description: 删除任务
     */
    @RequestMapping(value = "/controller/quartz/management/deleteJob", method = RequestMethod.POST)
    public Map<String, Object> deleteJobPlan(Quartz quartz){
        String userName = cmsUserServiceIntf.getUserByToken(quartz.getToken()).getName();
        quartz.setMakeUser(userName);
        quartz.setModifyUser(userName);
        return quartzServiceIntf.removeJob(quartz);
    }

    /**
     * @Author: RedRush
     * @Date:   2020/8/26 18:18
     * @description: 启动任务
     */
    @RequestMapping(value = "/controller/quartz/management/startJob", method = RequestMethod.POST)
    public Map<String, Object> startJobPlan(Quartz quartz){
        String userName = cmsUserServiceIntf.getUserByToken(quartz.getToken()).getName();
        quartz.setMakeUser(userName);
        quartz.setModifyUser(userName);
        return quartzServiceIntf.startJob(quartz);
    }

    /**
     * @Author: RedRush
     * @Date:   2020/8/26 18:21
     * @description: 停止任务
     */
    @RequestMapping(value = "/controller/quartz/management/stopJob", method = RequestMethod.POST)
    public Map<String, Object> stopJobPlan(Quartz quartz){
        String userName = cmsUserServiceIntf.getUserByToken(quartz.getToken()).getName();
        quartz.setMakeUser(userName);
        quartz.setModifyUser(userName);
        return quartzServiceIntf.stopJob(quartz);
    }

    /**
     * @Author: RedRush
     * @Date:   2020/8/27 9:04
     * @description: 计划回显
     */
    @RequestMapping(value = "/controller/quartz/management/returnJobView", method = RequestMethod.POST)
    public Map<String, Object> selectReturnView(Quartz quartz){
        return quartzServiceIntf.selectReturnView(quartz);
    }

    /**
     * @Author: RedRush
     * @Date:   2020/8/27 9:06
     * @description: 修改计划
     */
    @RequestMapping(value = "/controller/quartz/management/editJob", method = RequestMethod.POST)
    public Map<String, Object> editJobPlan(Quartz quartz){
        String userName = cmsUserServiceIntf.getUserByToken(quartz.getToken()).getName();
        quartz.setMakeUser(userName);
        quartz.setModifyUser(userName);
        return quartzServiceIntf.updateJobPlanAndParam(quartz);
    }

}
