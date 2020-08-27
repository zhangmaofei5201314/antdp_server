package com.donbala.logManage.service;

import com.donbala.logManage.model.LogTerm;

import java.util.List;

public interface LogQueryService {

     List<LogTerm> selectLog(LogTerm logTerm);
}
