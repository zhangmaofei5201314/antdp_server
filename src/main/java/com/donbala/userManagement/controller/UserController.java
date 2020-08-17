package com.donbala.userManagement.controller;

import ch.qos.logback.classic.Logger;
import com.donbala.userManagement.model.CmsUser;
import com.donbala.userManagement.service.CmsUserServiceIntf;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @CLassName: UserController
 * @Program: springbootdemo
 * @Author: wangran
 * @Date: 2019/7/10-14:08
 * @Description: todo
 **/
@Controller
public class UserController {

    public final static Logger log = (Logger) LoggerFactory.getLogger(UserController.class);

    @Autowired
    private CmsUserServiceIntf cmsUserService;


    @PostMapping("/controller/queryusers")
    @ResponseBody
    public List<CmsUser> queryUsers(CmsUser cmsUser) {
        List<CmsUser> cmsUsers = cmsUserService.queryUsers(cmsUser);
        return cmsUsers;
    }


    
}
