package com.donbala.quartzManagement.test.dao;

import com.donbala.quartzManagement.test.model.CommonTaskModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author RedRush
 * @date 2020/8/31 17:18
 * @description:
 */
public interface CommonTaskDao {

    List<CommonTaskModel> selectJobPlanParam(CommonTaskModel commonTaskModel);

    int insertJobRunLog(CommonTaskModel commonTaskModel);

    String selectLastSuccessDate(CommonTaskModel commonTaskModel);

    String selectLastSuccessDateByJobPlan(CommonTaskModel commonTaskModel);

    String selectHandAllMergePoolLastSuccessDate();

    String selectHandAllRefreshPoolLastSuccessDate();

    List<CommonTaskModel> selectJobRunLog(CommonTaskModel commonTaskModel);

    int selectJobRunLogCount(CommonTaskModel commonTaskModel);

    List<CommonTaskModel> selectJobName();

    int updateJobRunLogBySerialno(CommonTaskModel commonTaskModel);

    String selectJobNameByJobCode(@Param("jobCode") String jobCode);

}
