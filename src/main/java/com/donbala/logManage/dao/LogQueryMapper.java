package com.donbala.logManage.dao;

import com.donbala.logManage.model.LogTerm;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface LogQueryMapper {
      //查询日志信息
      List<LogTerm> queryLog(LogTerm logTerm);

}
