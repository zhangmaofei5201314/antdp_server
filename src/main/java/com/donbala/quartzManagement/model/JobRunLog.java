package com.donbala.quartzManagement.model;

import java.util.Date;

/**
 * @author RedRush
 * @date 2020/8/26 10:08
 */
public class JobRunLog {
    private String serialno;    // 流水号
    private String jobcode;     // 作业编码
    private String jobplancode; // 作业计划编码
    private Date startdate;     // 开始执行时间
    private Date enddate;       // 执行结束时间
    private String runstate;    // 执行状态
    private String result;      // 执行结果
    private Date makedate;      // 创建日期
    private String makeuser;    // 创建人
    private Date modifydate;    // 更改日期
    private String modifyuser;  // 更改人

    @Override
    public String toString() {
        return "JobRunLog{" +
                "serialno='" + serialno + '\'' +
                ", jobcode='" + jobcode + '\'' +
                ", jobplancode='" + jobplancode + '\'' +
                ", startdate=" + startdate +
                ", enddate=" + enddate +
                ", runstate='" + runstate + '\'' +
                ", result='" + result + '\'' +
                ", makedate=" + makedate +
                ", makeuser='" + makeuser + '\'' +
                ", modifydate=" + modifydate +
                ", modifyuser='" + modifyuser + '\'' +
                '}';
    }

    public String getSerialno() {
        return serialno;
    }

    public void setSerialno(String serialno) {
        this.serialno = serialno;
    }

    public String getJobcode() {
        return jobcode;
    }

    public void setJobcode(String jobcode) {
        this.jobcode = jobcode;
    }

    public String getJobplancode() {
        return jobplancode;
    }

    public void setJobplancode(String jobplancode) {
        this.jobplancode = jobplancode;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public String getRunstate() {
        return runstate;
    }

    public void setRunstate(String runstate) {
        this.runstate = runstate;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Date getMakedate() {
        return makedate;
    }

    public void setMakedate(Date makedate) {
        this.makedate = makedate;
    }

    public String getMakeuser() {
        return makeuser;
    }

    public void setMakeuser(String makeuser) {
        this.makeuser = makeuser;
    }

    public Date getModifydate() {
        return modifydate;
    }

    public void setModifydate(Date modifydate) {
        this.modifydate = modifydate;
    }

    public String getModifyuser() {
        return modifyuser;
    }

    public void setModifyuser(String modifyuser) {
        this.modifyuser = modifyuser;
    }
}
