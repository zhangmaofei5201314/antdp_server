package com.donbala.roleManagement.service;

import com.donbala.roleManagement.model.CmsRole;
import com.donbala.roleManagement.model.CmsRolemenu;

import java.util.List;
import java.util.Map;

public interface CmsRoleService{


    Map<String, Object> insert(String token, String roleName);


    List<String> getMenuByRoleid(String roleId);


    Map<String, Object> insertRoleMenu(CmsRole cmsRole, String token);
}
