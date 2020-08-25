package com.donbala.loginManagement.service.impl;

import com.donbala.loginManagement.dao.CmsLoginMapper;
import com.donbala.loginManagement.model.CmsLogintrace;
import com.donbala.loginManagement.service.LoginService;
import com.donbala.loginManagement.model.CmsMenu;
import com.donbala.userManagement.model.CmsUser;
import com.donbala.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * {\_/}
 * ( ^.^ )
 * / > @ zmf
 *
 * @date 2020/8/17
 */
@Service("loginService")
public class LoginServiceImpl implements LoginService {

    @Autowired
    private CmsLoginMapper cmsLoginMapper;

    /**
     * 记录登陆轨迹
     *  {\_/}
     * ( ^.^ )
     *  / > @ zhangmaofei
     * @date 2020/8/17 9:54
     * @param usercode 1
     * @param logintype 2
     * @return void
     */
    @Override
    public void saveLoginTrace(String usercode, String logintype) {
        CmsLogintrace cmsLogintrace = new CmsLogintrace();

        cmsLogintrace.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        cmsLogintrace.setUsercode(usercode);
        cmsLogintrace.setOperatedate(DateUtil.getSysDate());
        cmsLogintrace.setOperatetype(logintype);
        cmsLogintrace.setMakedate(DateUtil.getSysDate());
        cmsLogintrace.setMakeuser(usercode);
        cmsLogintrace.setModifydate(DateUtil.getSysDate());
        cmsLogintrace.setModifyuser(usercode);

        cmsLoginMapper.insert(cmsLogintrace);
    }
    /**
     * 查询用户并验证密码
     *  {\_/}
     * ( ^.^ )
     *  / > @ zhangmaofei
     * @date 2020/8/17 9:55
     * @param usermap 1
     * @return com.donbala.userManagement.model.CmsUser
     */
    @Override
    public CmsUser getUserByUsercode(Map<String, String> usermap) {
        CmsUser cmsUser = cmsLoginMapper.selectByUsercodeAndPsw(usermap);
        return cmsUser;
    }
    /**
     * 获取用户菜单
     *  {\_/}
     * ( ^.^ )
     *  / > @ zhangmaofei
     * @date 2020/8/17 9:55
     * @param usercode 1
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Override
    public List<Map<String, Object>> getAntdUserMenuByCode(String usercode) {
        List<CmsMenu> menus = cmsLoginMapper.selectMenuByUsercode(usercode);
        List<Map<String, Object>> rList = new ArrayList<>();

        if (menus.size()==0){
            return rList;
        }
        HashMap<String, ArrayList<CmsMenu>> hashMap = new HashMap<>(32);
        for (CmsMenu cm : menus) {
            if (!hashMap.containsKey(cm.getParentmenuid())) {
                hashMap.put(cm.getParentmenuid(), new ArrayList<CmsMenu>());
            }
            hashMap.get(cm.getParentmenuid()).add(cm);
        }

        ArrayList<CmsMenu> rootMenu = hashMap.get("0000");

        for (CmsMenu cmu : rootMenu){
            Map<String, Object> pMap = new HashMap<>();
            pMap.put("path", cmu.getMenulink());
            pMap.put("name", cmu.getMenucode());

            pMap.put("children", resolveMenu(hashMap, cmu.getMenuid()));
            rList.add(pMap);
        }
        return rList;
    }

    private List resolveMenu(HashMap<String, ArrayList<CmsMenu>> hashMap, String parentMenuId) {
        ArrayList<CmsMenu> list = hashMap.get(parentMenuId);
        if (list==null) {
            return null;
        }
        ArrayList<Map> retList = new ArrayList<>();
        for (CmsMenu cmsMenu : list) {
            Map<String, Object> cMap = new HashMap<>();
            cMap.put("path", cmsMenu.getMenulink());
            cMap.put("name", cmsMenu.getMenucode());
            List list1 = resolveMenu(hashMap, cmsMenu.getMenuid());
            if (list1!=null) {
                cMap.put("children", list1);
            }
            retList.add(cMap);
        }
        return retList;
    }

    /**
     * 获取用户菜单
     *  {\_/}
     * ( ^.^ )
     *  / > @ zhangmaofei
     * @date 2020/8/17 9:55
     * @param usercode 1
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    /*@Override
    public List<Map<String, Object>> getAntdUserMenuByCode(String usercode) {
        List<CmsMenu> menus = cmsLoginMapper.selectMenuByUsercode(usercode);
        List<Map<String, Object>> rList = new ArrayList<>();
        if(!(menus.size()==0)){

            for (CmsMenu cmu:menus) {
                Map<String, Object> pMap = new HashMap<>();
                if("0000".equals(cmu.getParentmenuid())){
                    pMap.put("path", cmu.getMenulink());
                    pMap.put("name", cmu.getMenucode());
                    List<Map<String, Object>> ChildMenus = new ArrayList<>();
                    for (CmsMenu cmuChild:menus) {

                        if(cmuChild.getParentmenuid().equals(cmu.getMenuid())){
                            Map<String, Object> cMap = new HashMap<>();
                            cMap.put("path", cmuChild.getMenulink());
                            cMap.put("name", cmuChild.getMenucode());
                            ChildMenus.add(cMap);
                        }

                    }
                    pMap.put("children", ChildMenus);
                    rList.add(pMap);
                }

            }
        }else {
            rList = new ArrayList<>();
        }



        return rList;
    }*/
}
