package com.donbala.batchJobsManager.model;

/**
 * TODO
 *
 * @author ljs
 * @version 1.0
 * @Descript
 * @date 2020/8/24 下午6:09
 */
public class JobModel {
    private String jobName;
    private String name;
    private String ip;
    private String frequency;
    private String startTime;
    private String startState;
    private String state;

    public JobModel(String jobName, String name, String ip, String frequency, String startTime, String startState, String state) {
        this.jobName = jobName;
        this.name = name;
        this.ip = ip;
        this.frequency = frequency;
        this.startTime = startTime;
        this.startState = startState;
        this.state = state;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getStartState() {
        return startState;
    }

    public void setStartState(String startState) {
        this.startState = startState;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
