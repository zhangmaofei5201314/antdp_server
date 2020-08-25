package com.donbala.batchJobsManager.controller;

import com.donbala.batchJobsManager.model.JobModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 * @author ljs
 * @version 1.0
 * @Descript
 * @date 2020/8/24 下午6:05
 */
@RestController
public class JobController {
    @PostMapping("/batchJobsManager/queryJobsInfo")
    public List queryJobsInfo(){
        ArrayList<JobModel> list = new ArrayList<>();
        list.add(new JobModel("活动积分清洗", "清洗活动产生的细分信息", "192.168.43.162", "one day a time", "today", "启用","1"));
        list.add(new JobModel("活动积分归并", "归并清洗活动产生的细分信息", "192.168.43.162", "one day a time", "today", "启用","1"));
        list.add(new JobModel("活动积分查询", "查询清洗活动产生的细分信息", "192.168.43.162", "one day a time", "today", "启用","1"));

        return list;
    }
}
