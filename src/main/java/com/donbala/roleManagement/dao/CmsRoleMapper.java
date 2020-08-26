package com.donbala.roleManagement.dao;

import org.apache.ibatis.annotations.Param;

import com.donbala.roleManagement.model.CmsRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CmsRoleMapper {

    int insert(CmsRole record);

    List<String> selectMenuByRoleid(@Param("roleid") String roleid);

    int deleteRoleMenuByRoleid(@Param("roleid") String roleid);

    int insertList(CmsRole cmsRole);

    int checkRoleName(String rolename);
}