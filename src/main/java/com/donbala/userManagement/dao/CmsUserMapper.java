package com.donbala.userManagement.dao;

import com.donbala.userManagement.model.CmsUser;

import java.util.List;

public interface CmsUserMapper {
    //查询用户列表
    List<CmsUser> selectUsers(CmsUser cmsUser);
    //通过用户号查询用户
    List<CmsUser> selectUserByCode(String usercode);
    //插入用户表
    int insertUser(CmsUser cmsUser);
    //插入用户角色表
    int inserUserRoles(CmsUser cmsUser);

    //删除用户
    int delteUser(String usercode);
    //删除用户的角色
    int deleteUserRoles(String usercode);

    //回显查询用户信息
    CmsUser selectUserReturnInfo(String usercode);

    //修改用户信息
    int updateUser(CmsUser cmsUser);

}