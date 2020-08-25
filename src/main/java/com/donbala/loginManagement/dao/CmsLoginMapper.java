package com.donbala.loginManagement.dao;

import com.donbala.loginManagement.model.CmsLogintrace;
import com.donbala.loginManagement.model.CmsMenu;
import com.donbala.userManagement.model.CmsUser;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public interface CmsLoginMapper {

    int insert(CmsLogintrace record);

    CmsUser selectByUsercodeAndPsw(Map<String, String> user);

    List<CmsMenu> selectMenuByUsercode(String usercode);
}