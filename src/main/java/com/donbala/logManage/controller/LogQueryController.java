package com.donbala.logManage.controller;

import com.donbala.logManage.model.LogTerm;
import com.donbala.logManage.service.LogQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LogQueryController {


    @Autowired
    private LogQueryService logQueryService;

    @RequestMapping(value = "/controller/log/querylog")
    public List<LogTerm> queryLog(LogTerm logTerm){

        List<LogTerm> result = logQueryService.selectLog(logTerm);

        for (LogTerm ll : result){
            if (ll.getRunState().equals("1")){
                ll.setRunState("已完成");
            }else if (ll.getRunState().equals("0")){
                ll.setRunState("执行中");
            }
        }
        return result;
    }
}
