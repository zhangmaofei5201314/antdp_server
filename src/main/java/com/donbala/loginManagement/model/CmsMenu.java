package com.donbala.loginManagement.model;

import java.util.Date;

public class CmsMenu {
    private String menuid;

    private String menuname;

    private String menucode;

    private String parentmenuid;

    private String menulink;

    private String status;

    private Integer nodeorder;

    private Date makedate;

    private String makeuser;

    private Date modifydate;

    private String modifyuser;

    public String getMenucode() {
        return menucode;
    }

    public void setMenucode(String menucode) {
        this.menucode = menucode;
    }

    public String getMenuid() {
        return menuid;
    }

    public void setMenuid(String menuid) {
        this.menuid = menuid;
    }

    public String getMenuname() {
        return menuname;
    }

    public void setMenuname(String menuname) {
        this.menuname = menuname;
    }

    public String getParentmenuid() {
        return parentmenuid;
    }

    public void setParentmenuid(String parentmenuid) {
        this.parentmenuid = parentmenuid;
    }

    public String getMenulink() {
        return menulink;
    }

    public void setMenulink(String menulink) {
        this.menulink = menulink;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getNodeorder() {
        return nodeorder;
    }

    public void setNodeorder(Integer nodeorder) {
        this.nodeorder = nodeorder;
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