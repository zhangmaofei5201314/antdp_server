package com.donbala.logManage.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.donbala.logManage.dao.LogQueryMapper;
import com.donbala.logManage.model.LogTerm;
import com.donbala.logManage.service.LogQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogQueryServiceImpl implements LogQueryService {

    @Autowired
    private LogQueryMapper logQueryMapper;

    @Override
    public List<LogTerm> selectLog(LogTerm logTerm){

        List<LogTerm> result = logQueryMapper.queryLog(logTerm);

        return result;
    }
}
