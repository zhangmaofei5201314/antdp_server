package com.donbala.loginManagement.controller;

import ch.qos.logback.classic.Logger;
import com.donbala.loginManagement.service.LoginService;
import com.donbala.userManagement.model.CmsUser;
import com.donbala.util.CacheManager;
import com.donbala.util.DateUtil;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
public class LoginController {

    public final static Logger log = (Logger) LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private LoginService loginService;
    @Autowired
    private CacheManager cache;


    @PostMapping("/api/login/account")
    @ResponseBody
    @Transactional
    public Map<String, Object> userLogin(String userName, String password) {
        System.out.println("username:"+userName+",password:"+password);
        Map<String, Object> map = new HashMap<>();
        if(userName==null||"".equals(userName)||password==null||"".equals(password)){
            map.put("status","error");
            map.put("code", "401");
            map.put("msg", "用户名或密码不可为空");
            return map;
        }
        Map<String, String> usermap = new HashMap<>();
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        usermap.put("usercode",userName);
        usermap.put("password",password);
        CmsUser cmsUser = loginService.getUserByUsercode(usermap);

        if (cmsUser == null) {
            map.put("status", "error");
            map.put("code", "401");
            map.put("msg", "用户名或密码错误");
        }
        else {
            loginService.saveLoginTrace(userName,"1");


            map.put("status", "ok");
            map.put("code", "200");

            map.put("userCode", cmsUser.getUsercode());
            map.put("userName", cmsUser.getName());
            //生成tokenId
            String token = UUID.randomUUID().toString().replaceAll("-","");
            String date = DateUtil.getSysDate();
            //向后端内存中放入token和用户信息--验证token用和根据token获取用户信息用
            Map<String, Object> tokenMap = new HashMap<>();
            tokenMap.put(token, date);
            tokenMap.put("user", cmsUser);
            // 这里存入缓存两次是为了可以通过用户名找到token和通过token获取用户等信息
            cache.addCache(token, tokenMap);
            cache.addCache(cmsUser.getUsercode(), token);

            map.put("token", token);



        }

        return map;
    }





    @PostMapping("/controller/antd/getUserMenu")
    @ResponseBody
    public Map<String, Object> getAntdUserMenu( String token){
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> tokenMap = (Map<String, Object>) cache.getValue(token);
        CmsUser cmsUser = (CmsUser) tokenMap.get("user");

        List<Map<String, Object>> menus = loginService.getAntdUserMenuByCode(cmsUser.getUsercode());
        map.put("code","200");
        map.put("menus", menus);

        return map;
    }

    /**
     *
     *  {\_/}
     * ( ^.^ )
     *  / > @ zhangmaofei
     * @date 2019/10/29 9:22
     * @param token 1
     * @return org.springframework.web.servlet.ModelAndView
     */
    @PostMapping("/userlogout")
    @ResponseBody
    public void logout(String token){
        System.out.println("退出登陆："+token);
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> tokenMap = (Map<String, Object>) cache.getValue(token);
        CmsUser cmsUser = (CmsUser) tokenMap.get("user");
        if(cmsUser!=null){
            cache.deleteCache(token);
            cache.deleteCache(cmsUser.getUsercode());
            loginService.saveLoginTrace(cmsUser.getUsercode(),"0");

        }
    }

}
