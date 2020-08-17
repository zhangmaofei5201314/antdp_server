package com.donbala.codeselect.service.impl;

import com.donbala.codeselect.dao.CodeAndNameMapper;
import com.donbala.codeselect.model.CodeAndName;
import com.donbala.codeselect.service.CodeSelectIntf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

}
