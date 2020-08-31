package com.donbala.quartzManagement.test.jobs;

import ch.qos.logback.classic.Logger;
import com.donbala.quartzManagement.test.dao.CommonTaskDao;
import com.donbala.quartzManagement.test.model.CommonTaskModel;
import com.donbala.quartzManagement.test.service.DemoTaskService;
import com.donbala.util.DateUtil;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author RedRush
 * @date 2020/8/31 17:14
 * @description: 测试任务日志功能
 */
@DisallowConcurrentExecution
public class JobTestLog implements Job {

    public final static Logger log = (Logger) LoggerFactory.getLogger(JobTestLog.class);
    @Autowired
    CommonTaskDao commonTaskDao;
    @Autowired
    DemoTaskService demoTaskService;


    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        String jobCode = context.getJobDetail().getKey().getName();
        String jobPlanCode = context.getJobDetail().getKey().getGroup();
        log.info("作业code："+ context.getJobDetail().getKey().getName());
        log.info("计划code："+ context.getJobDetail().getKey().getGroup());

        CommonTaskModel commonTaskModel = new CommonTaskModel();
        commonTaskModel.setJobCode(jobCode);
        commonTaskModel.setJobPlanCode(jobPlanCode);
        //查询该任务计划的参数
        List<CommonTaskModel> listParam = commonTaskDao.selectJobPlanParam(commonTaskModel);
        Map<String,String> paramMap = new HashMap<String,String>();
        for(CommonTaskModel param : listParam) {
            paramMap.put(param.getParamCode(), param.getParamValue());
        }
        //获取执行计划的ip地址
//        String ip = paramMap.get("IP").toString();
        //这里是个demo，不限制ip，需要的话放开
        String ip = DateUtil.getLocalHostIP();
        log.info("任务计划:"+jobPlanCode+",任务:"+jobCode+",设定执行的ip地址为："+ip);
        String localAddress= DateUtil.getLocalHostIP();
        //获取本机IP地址
        log.info("本机ip地址为:"+localAddress);
        //如果本地ip是任务计划要执行的ip则执行作业任务
        if(localAddress.equals(ip)) {
            log.info("执行批处理");
            demoTaskService.demoTask(jobCode, jobPlanCode);
        }else {
            log.info("不执行");
        }
    }
}

