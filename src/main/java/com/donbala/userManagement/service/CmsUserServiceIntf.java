package com.donbala.userManagement.service;

import com.donbala.userManagement.model.CmsUser;

import java.util.List;
import java.util.Map;

public interface CmsUserServiceIntf {

     List<CmsUser> queryUsers(CmsUser cmsUser);

     CmsUser getUserByToken(String token);

     Map<String, Object> saveUser(CmsUser cmsUser);

     Map<String, Object> deleteUser(String usercode);

     CmsUser queryUserReturnInfo(String usercode);

     Map<String, Object> editUser(CmsUser cmsUser);
}
