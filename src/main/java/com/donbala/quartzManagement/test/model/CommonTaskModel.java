package com.donbala.quartzManagement.test.model;

import com.donbala.Commons.model.BaseResult;

import java.io.Serializable;

/**
 * @author RedRush
 * @date 2020/8/31 17:15
 * @description: 公共的任务实体类
 */
public class CommonTaskModel extends BaseResult implements Serializable {
    //流水号
    private String serialno;
    //作业编码
    private String jobCode;
    //作业名称
    private String jobName;
    //计划编码
    private String jobPlanCode;
    //开始时间
    private String startDate;
    //结束时间
    private String endDate;
    //运行状态
    private String runState;
    //运行结果
    private String runResult;
    //创建日期
    private String makeDate;
    //创建人
    private String makeUser;
    //更改日期
    private String modifyDate;
    //更改人
    private String modifyUser;
    //参数代码
    private String paramCode;
    //参数值
    private String paramValue;
    //作业类型
    private String jobType;
    //批次号
    private String batchno;

    public String getSerialno() {
        return serialno;
    }

    public void setSerialno(String serialno) {
        this.serialno = serialno;
    }

    public String getJobCode() {
        return jobCode;
    }

    public void setJobCode(String jobCode) {
        this.jobCode = jobCode;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobPlanCode() {
        return jobPlanCode;
    }

    public void setJobPlanCode(String jobPlanCode) {
        this.jobPlanCode = jobPlanCode;
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

    public String getRunState() {
        return runState;
    }

    public void setRunState(String runState) {
        this.runState = runState;
    }

    public String getRunResult() {
        return runResult;
    }

    public void setRunResult(String runResult) {
        this.runResult = runResult;
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

    public String getParamCode() {
        return paramCode;
    }

    public void setParamCode(String paramCode) {
        this.paramCode = paramCode;
    }

    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getBatchno() {
        return batchno;
    }

    public void setBatchno(String batchno) {
        this.batchno = batchno;
    }
}

