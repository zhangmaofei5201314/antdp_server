package com.donbala.userManagement.dao;

import com.donbala.userManagement.model.CmsUser;

import java.util.List;

public interface CmsUserMapper {

    List<CmsUser> selectUsers(CmsUser cmsUser);

}