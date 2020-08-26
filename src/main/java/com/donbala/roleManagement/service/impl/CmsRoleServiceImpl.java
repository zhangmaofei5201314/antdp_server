package com.donbala.roleManagement.service.impl;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import com.donbala.Commons.controller.Common;
import com.donbala.roleManagement.model.CmsRolemenu;
import com.donbala.userManagement.model.CmsUser;
import com.donbala.util.CacheManager;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.donbala.roleManagement.dao.CmsRoleMapper;
import com.donbala.roleManagement.model.CmsRole;
import com.donbala.roleManagement.service.CmsRoleService;
@Service
public class CmsRoleServiceImpl implements CmsRoleService{

    @Resource
    private CmsRoleMapper cmsRoleMapper;
    @Resource
    private CacheManager cache;

    /**
     * 添加角色
     * @param token
     * @param roleName
     * @return
     */
    @Override
    public Map<String, Object> insert(String token, String roleName) {
        int i = cmsRoleMapper.checkRoleName(roleName);
        if(i > 0){
            return Common.insertFail("203","角色已存在");
        }
        else {
            Map<String, Object> tokenMap = (Map<String,Object>)cache.getValue(token);
            CmsUser cmsUser = (CmsUser) tokenMap.get("user");
            String s = UUID.randomUUID().toString().replace("-","");
            CmsRole role = new CmsRole();
            role.setRoleid(s);
            role.setRolename(roleName);
            role.setMakeuser(cmsUser.getUsercode());
            role.setModifyuser(cmsUser.getUsercode());
            cmsRoleMapper.insert(role);
            return Common.insertSuccess("新增成功");
        }

    }

    @Override
    public List<String> getMenuByRoleid(String roleId){
        List<String> cmsRolemenus = cmsRoleMapper.selectMenuByRoleid(roleId);
        return cmsRolemenus;
    }

    @Override
    public Map<String, Object> insertRoleMenu(CmsRole cmsRole,String token){

        cmsRoleMapper.deleteRoleMenuByRoleid(cmsRole.getRoleid());
        Map<String, Object> tokenMap = (Map<String,Object>)cache.getValue(token);
        CmsUser cmsUser = (CmsUser) tokenMap.get("user");
        cmsRole.setMakeuser(cmsUser.getUsercode());
        cmsRole.setModifyuser(cmsUser.getUsercode());
        cmsRoleMapper.insertList(cmsRole);
        return Common.insertSuccess("添加成功");


    }

}
