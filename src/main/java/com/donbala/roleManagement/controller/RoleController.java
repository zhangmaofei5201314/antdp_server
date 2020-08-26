package com.donbala.roleManagement.controller;

import com.donbala.Commons.controller.Common;
import com.donbala.roleManagement.model.CmsRole;
import com.donbala.roleManagement.model.CmsRolemenu;
import com.donbala.roleManagement.service.CmsRoleService;
import com.donbala.userManagement.controller.UserController;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;

@RestController
public class RoleController {

    @Autowired
    CmsRoleService cmsRoleService;

    public final static ch.qos.logback.classic.Logger log = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(UserController.class);


    /**
     * 插入角色
     * @param token
     * @param roleName
     * @return
     */
    @PostMapping("/controller/insterRole")
    public Map<String, Object> insterRole(String token, String roleName){
        try{
            if(roleName == null){
                return Common.insertFail("201","数据为空");
            }
            return cmsRoleService.insert(token,roleName);

        }catch (Exception e){
            log.info("新增用户报错："+e.getMessage());
            return Common.insertFail("500","插入失败");
        }

    }


    /**
     * 获取角色拥有的权限
     * @param roleId
     * @return
     */
    @PostMapping("/controller/getMenuByRoleid")
    public List<String> getMenuByRoleid(String roleId){
        return cmsRoleService.getMenuByRoleid(roleId);
    }


    /**
     * 添加角色的权限
     * @param cmsRole
     * @return
     */
    @PostMapping("/controller/insertRoleMenu")
    public Map<String, Object> insertRoleMenu (CmsRole cmsRole, String token){
        try{
            return cmsRoleService.insertRoleMenu(cmsRole,token);
        }catch (Exception e){
            log.info("添加角色权限报错："+e.getMessage());
            return Common.insertFail("500","插入失败");
        }
    }


}
