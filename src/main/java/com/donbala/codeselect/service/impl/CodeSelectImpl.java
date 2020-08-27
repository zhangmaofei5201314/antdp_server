package com.donbala.codeselect.service.impl;

import com.donbala.codeselect.dao.CodeAndNameMapper;
import com.donbala.codeselect.model.CodeAndName;
import com.donbala.codeselect.service.CodeSelectIntf;
import com.donbala.loginManagement.model.CmsMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @CLassName: CodeSelectImpl
 * @Program: springbootdemo
 * @Author: wangran
 * @Date: 2019/7/12-14:15
 * @Description: todo
 **/
@Service
public class CodeSelectImpl implements CodeSelectIntf {

    @Autowired
    private CodeAndNameMapper codeAndNameMapper;

    /**
    *@description: 主要入口函数，返回要查询的编码和编码名称
    */
    public List<CodeAndName> codeSelect(String codetype) {
        List<CodeAndName> codeAndNameList = new ArrayList<>();

        if(codetype.equals("role")){
            codeAndNameList = getRoleList();
        }else if (codetype.equals("menu")){
            codeAndNameList = getRoleList();
        }else if (codetype.equals("job")){
            codeAndNameList = getJobName();
        }

        return codeAndNameList;
    }

    /**
     * 角色下拉
     *  {\_/}
     * ( ^.^ )
     *  / > @ zhangmaofei
     * @date 2019/9/18 9:18
     * @param
     * @return java.util.List<com.donbala.codeselect.model.CodeAndName>
     */
    private List<CodeAndName> getRoleList() {
        List<CodeAndName> codeAndNameList = codeAndNameMapper.selectRole() ;
        return codeAndNameList;
    }

    private List<CodeAndName> getJobName(){
        List<CodeAndName> codeAndNameList = codeAndNameMapper.selectJobName() ;
        return codeAndNameList;
    }

    /**
     * 菜单列表
     * @return
     */
//    private List<CodeAndName> getMenuList() {
//        List<CodeAndName> codeAndNameList = codeAndNameMapper.selectMenu();
//        return codeAndNameList;
//    }

    private List<Map<String, Object>> getMenuList(){
        List<CmsMenu> menus = codeAndNameMapper.selectMenu();
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
            pMap.put("title", cmu.getMenuname());
            pMap.put("value", cmu.getMenuid());
            pMap.put("key", cmu.getMenuid());

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
            cMap.put("title", cmsMenu.getMenuname());
            cMap.put("value", cmsMenu.getMenuid());
            cMap.put("key", cmsMenu.getMenuid());
            List list1 = resolveMenu(hashMap, cmsMenu.getMenuid());
            if (list1!=null) {
                cMap.put("children", list1);
            }
            retList.add(cMap);
        }
        return retList;
    }

    @Override
    public List<Map<String, Object>> treeSelect(String codetype) {
        List<Map<String, Object>> rList = new ArrayList<>();
        if("menu".equals(codetype)){
            rList = getMenuList();
        }
        return rList;
    }
}
