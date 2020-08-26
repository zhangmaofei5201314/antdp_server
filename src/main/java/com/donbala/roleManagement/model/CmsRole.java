package com.donbala.roleManagement.model;

import com.donbala.Commons.model.BaseResult;

import java.util.Date;

public class CmsRole extends BaseResult {
    private String roleid;

    private String rolename;

    private Date makedate;

    private String makeuser;

    private Date modifydate;

    private String modifyuser;

    private String [] cmsRoleMenu;

    public String getRoleid() {
        return roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
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

    public String[] getCmsRoleMenu() {
        return cmsRoleMenu;
    }

    public void setCmsRoleMenu(String[] cmsRoleMenu) {
        this.cmsRoleMenu = cmsRoleMenu;
    }
}