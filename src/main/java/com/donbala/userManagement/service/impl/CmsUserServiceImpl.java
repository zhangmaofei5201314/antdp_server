package com.donbala.userManagement.service.impl;

import com.donbala.Commons.controller.Common;
import com.donbala.userManagement.dao.CmsUserMapper;
import com.donbala.userManagement.model.CmsUser;
import com.donbala.userManagement.service.CmsUserServiceIntf;
import com.donbala.util.CacheManager;
import com.donbala.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    /**
     * 新增用户
     *  {\_/}
     * ( ^.^ )
     *  / > @ zhangmaofei
     * @date 2020/8/18 11:19
     * @param cmsUser 1
     * @return void
     */
    @Override
    @Transactional
    public Map<String, Object> saveUser(CmsUser cmsUser) {
        List<CmsUser> users = cmsUserMapper.selectUserByCode(cmsUser.getUsercode());
        if(users.size()!=0){
            return Common.insertFail("202", "用户已存在");
        }
        String password="000000";
        try {
            password= MD5Util.md5Encode(password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        cmsUser.setPassword(password);
        CmsUser op = getUserByToken(cmsUser.getToken());
        cmsUser.setMakeuser(op.getUsercode());
        cmsUser.setModifyuser(op.getUsercode());
        cmsUserMapper.insertUser(cmsUser);
        cmsUserMapper.inserUserRoles(cmsUser);
        return Common.insertSuccess("新增成功");
    }
    //删除用户
    @Override
    @Transactional
    public Map<String, Object> deleteUser(String usercode) {
        cmsUserMapper.delteUser(usercode);
        cmsUserMapper.deleteUserRoles(usercode);
        return Common.deleteSuccess("删除成功");
    }

    //用户回显信息
    @Override
    public CmsUser queryUserReturnInfo(String usercode) {

        CmsUser cmsUser = cmsUserMapper.selectUserReturnInfo(usercode);
        if(cmsUser==null){
            return new CmsUser();
        }else {
            String[] roles = cmsUser.getDepartment().split(",");
            cmsUser.setCmsUserroles(roles);
            return cmsUser;
        }
    }
    //修改用户
    @Override
    @Transactional
    public Map<String, Object> editUser(CmsUser cmsUser) {
        CmsUser op = getUserByToken(cmsUser.getToken());
        cmsUser.setMakeuser(op.getUsercode());
        cmsUser.setModifyuser(op.getUsercode());

        cmsUserMapper.deleteUserRoles(cmsUser.getUsercode());
        cmsUserMapper.updateUser(cmsUser);
        cmsUserMapper.inserUserRoles(cmsUser);

        return Common.insertSuccess("修改成功");
    }
}
