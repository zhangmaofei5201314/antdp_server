package com.donbala.quartzManagement.dao;

import com.donbala.quartzManagement.model.Quartz;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QuartzMapper {

    // 获取全部任务执行计划
    List<Quartz> getAllJobPlan();
    // 查询可用的任务执行计划
    List<Quartz> getAvailableJobPlan();
    // 查询作业参数
    List<Quartz> selectJobParam(Quartz qz);
    // 新增作业计划
    int insertJobPlanDef(Quartz qz);
    // 记录作业计划参数
    int insertJobPlanParam(Quartz qz);
    // 删除任务
    int deleteJobPlanDef(String jobPlanCode);
    // 删除任务参数
    int deleteJobPlanParam(String jobPlanCode);
    // 修改作业任务
    int updateJobPlan(Quartz qz);
    // 删除作业参数
    int deletePlanParam(Quartz qz);
    // 变更作业状态为启动
    int startJobPlanDef(String jobPlanCode);
    // 变更作业状态为停止
    int stopJobPlanDef(String jobPlanCode);
    // 查询作业内容，job类名
    Quartz selectJobPlanClass(String jobPlanCode);
    // 查询计划运行状态
    Quartz selectJobPlanState(String jobPlanCode);
    // 计划回显
    List<Quartz> selectReturnView(Quartz qz);
    // 参数回显
    List<Quartz> selectParamByCode(Quartz qz);


//    int selectJobPlanCount(Quartz qz);
//    List<Quartz> selectJobName();
//    List<Quartz> selectRepeatUnit();
//    // 查询作业参数
//    // 更新任务执行日志
//    void updateJobRunLog(Map<String, String> map);
//    // 查询运行日志的流水号，以后应该不用，暂时保留日后优化
//    String selectJobRunLogSerialNO(Map<String, String> map);
//    // 更新作业状态为启动
//    void updateJobPlanStateStart(@Param("jobPlanCode") String jobPlanCode);
//    // 更新作业状态为停止
//    void updateJobPlanStateTerminate(@Param("jobPlanCode") String jobPlanCode);
}

