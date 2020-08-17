package com.donbala.codeselect.controller;

import com.donbala.codeselect.model.CodeAndName;
import com.donbala.codeselect.service.CodeSelectIntf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @CLassName: CodeSelectController
 * @Program: springbootdemo
 * @Author: wangran
 * @Date: 2019/7/12-14:05
 * @Description: todo
 **/
@RestController
public class CodeSelectController {

    @Autowired
    private CodeSelectIntf codeSelect;

    @GetMapping("/controller/codeselect")
    public List<CodeAndName> codeSelect(String codetype) {
        List<CodeAndName> codeAndNameList = codeSelect.codeSelect(codetype);
        return codeAndNameList;
    }

}
