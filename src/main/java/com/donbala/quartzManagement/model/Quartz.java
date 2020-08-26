package com.donbala.quartzManagement.model;

import com.donbala.Commons.model.BaseResult;

public class Quartz extends BaseResult {

    /**
     * 定时任务类
     */
    private static final long serialVersionUID = 1L;

    private String jobCode;     // 任务代码
    private String jobName;     // 任务名称
    private String jobDescribe; // 任务描述
    private String jobClassName;// 任务类名
    private String makeDate;    // 创建日期
    private String makeUser;    // 创建人
    private String modifyDate;  // 更改日期
    private String modifyUser;  // 更改人

    private String jobPlanCode;     // 计划代码
    private String jobPlanDesc;     // 计划描述
    private String runType;         // 运行方式类型
    private String cronExp;         // Cron表达式
    private String cronExpDesc;     // Cron描述
    private String repeatInterval;  // 循环间隔数值
    private String repeatUnit;      // 执行频率（循环单位）

    private String startDate;       // 任务生效日期
    private String endDate;         // 任务失效日期
    private String useFlag;         // 是否启用
    private String triggerState;    // 运行状态
    private String paramValue;      // 参数值
    private String paramName;       // 参数名称
    private String paramCode;       // 参数编码
    private String valueAlias;      // 取值范围

    /*参数*/
    private String paramStartDate;
    private String paramEndDate;
    private String runState;
    private String IP;

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public String getRunState() {
        return runState;
    }

    public void setRunState(String runState) {
        this.runState = runState;
    }

    public String getValueAlias() {
        return valueAlias;
    }

    public void setValueAlias(String valueAlias) {
        this.valueAlias = valueAlias;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public String getParamStartDate() {
        return paramStartDate;
    }

    public void setParamStartDate(String paramStartDate) {
        this.paramStartDate = paramStartDate;
    }

    public String getParamEndDate() {
        return paramEndDate;
    }

    public void setParamEndDate(String paramEndDate) {
        this.paramEndDate = paramEndDate;
    }

    public String getJobPlanDesc() {
        return jobPlanDesc;
    }

    public void setJobPlanDesc(String jobPlanDesc) {
        this.jobPlanDesc = jobPlanDesc;
    }

    public String getJobPlanCode() {
        return jobPlanCode;
    }

    public void setJobPlanCode(String jobPlanCode) {
        this.jobPlanCode = jobPlanCode;
    }

    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

    public String getParamCode() {
        return paramCode;
    }

    public void setParamCode(String paramCode) {
        this.paramCode = paramCode;
    }

    public String getRunType() {
        return runType;
    }

    public void setRunType(String runType) {
        this.runType = runType;
    }

    public String getCronExp() {
        return cronExp;
    }

    public void setCronExp(String cronExp) {
        this.cronExp = cronExp;
    }

    public String getCronExpDesc() {
        return cronExpDesc;
    }

    public void setCronExpDesc(String cronExpDesc) {
        this.cronExpDesc = cronExpDesc;
    }

    public String getRepeatInterval() {
        return repeatInterval;
    }

    public void setRepeatInterval(String repeatInterval) {
        this.repeatInterval = repeatInterval;
    }

    public String getRepeatUnit() {
        return repeatUnit;
    }

    public void setRepeatUnit(String repeatUnit) {
        this.repeatUnit = repeatUnit;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getUseFlag() {
        return useFlag;
    }

    public void setUseFlag(String useFlag) {
        this.useFlag = useFlag;
    }

    public String getTriggerState() {
        return triggerState;
    }

    public void setTriggerState(String triggerState) {
        this.triggerState = triggerState;
    }

    public String getJobCode() {
        return jobCode;
    }

    public void setJobCode(String jobCode) {
        this.jobCode = jobCode;
    }

    public String getJobDescribe() {
        return jobDescribe;
    }

    public void setJobDescribe(String jobDescribe) {
        this.jobDescribe = jobDescribe;
    }

    public String getJobClassName() {
        return jobClassName;
    }

    public void setJobClassName(String jobClassName) {
        this.jobClassName = jobClassName;
    }

    public String getMakeDate() {
        return makeDate;
    }

    public void setMakeDate(String makeDate) {
        this.makeDate = makeDate;
    }

    public String getMakeUser() {
        return makeUser;
    }

    public void setMakeUser(String makeUser) {
        this.makeUser = makeUser;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getModifyUser() {
        return modifyUser;
    }

    public void setModifyUser(String modifyUser) {
        this.modifyUser = modifyUser;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

}