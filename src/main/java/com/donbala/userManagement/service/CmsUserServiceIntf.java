package com.donbala.userManagement.service;

import com.donbala.userManagement.model.CmsUser;

import java.util.List;

public interface CmsUserServiceIntf {

     List<CmsUser> queryUsers(CmsUser cmsUser);

     CmsUser getUserByToken(String token);


}
