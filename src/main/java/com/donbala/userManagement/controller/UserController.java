package com.donbala.userManagement.controller;

import ch.qos.logback.classic.Logger;
import com.donbala.Commons.controller.Common;
import com.donbala.userManagement.model.CmsUser;
import com.donbala.userManagement.service.CmsUserServiceIntf;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @CLassName: UserController
 * @Program: springbootdemo
 * @Author: wangran
 * @Date: 2019/7/10-14:08
 * @Description: todo
 **/
@RestController
public class UserController {

    public final static Logger log = (Logger) LoggerFactory.getLogger(UserController.class);

    @Autowired
    private CmsUserServiceIntf cmsUserService;

    //查询用户列表
    @PostMapping("/controller/queryusers")
    public List<CmsUser> queryUsers(CmsUser cmsUser) {
        List<CmsUser> cmsUsers = cmsUserService.queryUsers(cmsUser);
        return cmsUsers;
    }
    //保存用户
    @PostMapping("/controller/saveUser")
    public Map<String, Object> saveUser(CmsUser cmsUser){
        try {
            if (cmsUser == null) {
                System.out.println("数据为空！");
                return Common.insertFail("201","数据为空");
            }


            return cmsUserService.saveUser(cmsUser);

        } catch (Exception e) {
            log.info("新增用户报错："+e.getMessage());
            return Common.insertFail("500","插入失败");
        }

    }
    //删除用户
    @PostMapping("/controller/deleteUser")
    public Map<String, Object> deleteUser(CmsUser cmsUser){
        try {
            if (cmsUser == null) {
                System.out.println("数据为空！");
                return Common.insertFail("201","数据为空");
            }

            return cmsUserService.deleteUser(cmsUser.getUsercode());

        } catch (Exception e) {
            log.info("新增用户报错："+e.getMessage());
            return Common.insertFail("500","插入失败");
        }

    }
    //用户信息回显
    @PostMapping("/controller/userInfoReturn")
    public CmsUser selectUserInfoByCode(String usercode){

        return cmsUserService.queryUserReturnInfo(usercode);
    }

    //修改用户
    @PostMapping("/controller/editUser")
    public Map<String, Object> editUser(CmsUser cmsUser){
        try {
            if (cmsUser == null) {
                System.out.println("数据为空！");
                return Common.insertFail("201","数据为空");
            }

            return cmsUserService.editUser(cmsUser);
        } catch (Exception e) {
            log.info("修改用户报错："+e.getMessage());
            return Common.insertFail("500","插入失败");
        }

    }
    
}
