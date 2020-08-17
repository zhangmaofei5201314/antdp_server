package com.donbala.userManagement.service.impl;

import com.donbala.userManagement.dao.CmsUserMapper;
import com.donbala.userManagement.model.CmsUser;
import com.donbala.userManagement.service.CmsUserServiceIntf;
import com.donbala.util.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CmsUserServiceImpl implements CmsUserServiceIntf {

    @Autowired
    private CmsUserMapper cmsUserMapper;

    @Autowired
    private CacheManager cache;


    /**
     *@description: 根据页面的查询条件查询客户列表
     */
    @Override
    public List<CmsUser> queryUsers(CmsUser cmsUser) {
        List<CmsUser> userList = cmsUserMapper.selectUsers(cmsUser);
        return userList;
    }


    /**
     * 根据token获取用户对象信息
     *  {\_/}
     * ( ^.^ )
     *  / > @ zhangmaofei
     * @date 2020/4/14 15:07
     * @param token 1
     * @return com.donbala.userManagement.model.CmsUser
     */
    @Override
    public CmsUser getUserByToken(String token) {
        Map<String, Object> tokenMap = (Map<String, Object>) cache.getValue(token);
        CmsUser cmsUser = (CmsUser) tokenMap.get("user");
        return cmsUser;
    }
}
