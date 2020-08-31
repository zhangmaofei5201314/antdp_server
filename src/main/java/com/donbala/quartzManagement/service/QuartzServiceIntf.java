package com.donbala.quartzManagement.service;

import com.donbala.quartzManagement.model.Quartz;

import java.util.List;
import java.util.Map;

public interface QuartzServiceIntf {

    // 查询任务表格
    List<Quartz> getJobPlanList();
    // 查询作业参数
    List<Quartz> getJobParamList(String jobCode);
    // 新增作业计划
    Map<String, Object> insertJobAndParam(Quartz quartz);
    // 删除一个任务
    Map<String, Object> removeJob(Quartz quartz);
    // 启动一个任务
    Map<String, Object> startJob(Quartz quartz);
    // 停止一个任务
    Map<String, Object> stopJob(Quartz quartz);
    // 计划回显
    Map<String, Object> selectReturnView(Quartz quartz);
    // 修改一个任务
    Map<String, Object> updateJobPlanAndParam(Quartz quartz);
    // 获取所有定时任务，添加到调度器中
    void initJobsOnstart();
}
