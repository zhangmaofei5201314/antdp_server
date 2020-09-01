package com.donbala.quartzManagement.service.impl;

import ch.qos.logback.classic.Logger;
import com.alibaba.fastjson.JSONObject;
import com.donbala.quartzManagement.MyTriggerListener;
import com.donbala.quartzManagement.dao.QuartzMapper;
import com.donbala.quartzManagement.model.Quartz;
import com.donbala.quartzManagement.service.QuartzServiceIntf;
import com.donbala.quartzManagement.util.QuartzUtils;
import com.donbala.util.DateUtil;
import org.quartz.*;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author: RedRush
 * @Date:   2020/8/25 9:57
 *   定义运行状态    useFlag   trggerState
 *     停止运行        0         无
 *     等待运行        1         0
 *     正在运行        1         1
 */
@Service
public class QuartzServiceImpl implements QuartzServiceIntf {

    public final static Logger log = (Logger) LoggerFactory.getLogger(QuartzServiceImpl.class);

    @Resource
    private QuartzMapper quartzMapper;

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;


    /**
     * @Author: RedRush
     * @Date:   2020/8/24 18:07
     * @description: 查询全部任务执行计划
     */
    @Override
    public List<Quartz> getJobPlanList() {
        return quartzMapper.getAllJobPlan();
    }

    /**
     * @Author: RedRush
     * @Date:   2020/8/24 18:07
     * @description: 查询作业参数
     */
    @Override
    public List<Quartz> getJobParamList(String jobCode) {
        Quartz quartz = new Quartz();
        quartz.setJobCode(jobCode);
        return quartzMapper.selectJobParam(quartz);
    }

    /**
     * @Author: RedRush
     * @Date:   2020/8/28 9:45
     * @description: 新增作业计划
     */
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public Map<String, Object> insertJobAndParam(Quartz quartz) {
        // ParamValue参数非空判断
        if (quartz.getParamValue() == null || "".equals(quartz.getParamValue()) || quartz.getParamValue().length() == 0){
            return basicMapBuilder("warning", "参数为空", "502");
        }
        // 前端参数ParamValue： {IP:10.1.18.56,endDate:2019-10-23 15:10:20,startDate:2019-10-23 15:10:19}
        Map<String, Object> paramValues = JSONObject.parseObject(quartz.getParamValue());
        // // paramvalue中根据key获取IP，并校验
        String localIP = DateUtil.getLocalHostIP();
//        if (!(paramValues.containsKey("IP") && localIP.equals(paramValues.get("IP")))){
//            return basicMapBuilder("ipError", "IP异常", "503");
//        }
        // 获取当前日期字符串
        String dateStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        if (quartz.getStartDate() == null || "".equals(quartz.getStartDate())){ // 如果任务开始日期为空，则赋值为当前时间
            quartz.setStartDate(dateStr);
        }
        if (quartz.getEndDate() == null || "".equals(quartz.getEndDate())){ // 如果任务结束日期为空，则赋值为无穷
            quartz.setStartDate("9999-12-31 00:00:00");
        }
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        quartz.setJobPlanCode(uuid);
        quartz.setMakeDate(dateStr);
        quartz.setModifyDate(dateStr);
        // 新增作业计划时，默认设置为停止，需要手动启动
        quartz.setUseFlag("0");         // 是否启用，0-停止，1-启动
        quartz.setTriggerState("0");    // 运行状态，0-停止，1-启动
        // 算出cron表达式，存放到数据库中
        quartz.setCronExp(QuartzUtils.cron(quartz));
        quartz.setRunType("0");         // 设置任务类型，0-cron任务
        quartzMapper.insertJobPlanDef(quartz);
        List<Quartz> dataList = quartzMapper.selectJobParam(quartz);
        for (String paramKey : paramValues.keySet()) {
            for (Quartz qtData: dataList) {
                if (qtData.getParamCode().equals(paramKey)){
                    quartz.setParamCode(qtData.getParamCode());
                    quartz.setParamValue((String) paramValues.get(paramKey));
                    quartzMapper.insertJobPlanParam(quartz);
                    System.out.println(quartz.getParamCode() + "," + quartz.getParamValue());
                }
            }
        }
        // 将新生成的作业添加到调度里，开始执行作业
        //Common.addQuartz(quartz, quartzService);
        return basicMapBuilder("success", "执行计划新增成功", "200");
    }

    /**
     * @Author: RedRush
     * @Date:   2020/8/28 9:46
     * @description: 删除一个任务
     */
    @Override
    @Transactional(isolation= Isolation.READ_COMMITTED,propagation= Propagation.REQUIRED)
    public Map<String, Object> removeJob(Quartz quartz) {
        // ParamValue参数非空判断
        if (quartz.getParamValue() == null || "".equals(quartz.getParamValue()) || quartz.getParamValue().length() == 0){
            return basicMapBuilder("warning", "参数为空", "502");
        }
        // 前端参数ParamValue： {IP:10.1.18.56,endDate:2019-10-23 15:10:20,startDate:2019-10-23 15:10:19}
//        Map<String, Object> paramValues = JSONObject.parseObject(quartz.getParamValue());
        String IP = quartz.getParamValue();
        // // paramvalue中根据key获取IP，并校验
        String localIP = DateUtil.getLocalHostIP();
//        if (!(paramValues.containsKey("IP") && localIP.equals(paramValues.get("IP")))){
//            return basicMapBuilder("ipError", "IP异常", "503");
//        }
        try {
            // 提取出jobPlanCode，放入map，并根据jobPlanCode删除任务和任务参数
            String jobPlanCode = quartz.getJobPlanCode();
            quartzMapper.deleteJobPlanDef(jobPlanCode);
            quartzMapper.deleteJobPlanParam(jobPlanCode);
            // 创建调度容器,并根据jobPlanCode生成触发器key
            Scheduler  scheduler = schedulerFactoryBean.getScheduler();
            TriggerKey triggerKey = TriggerKey.triggerKey(jobPlanCode, jobPlanCode);
            // 根据触发器key值，停止对应的触发器，移除该触发器，删除该任务
            scheduler.pauseTrigger(triggerKey);         // 停止触发器
            scheduler.unscheduleJob(triggerKey);        // 移除触发器
            scheduler.deleteJob(JobKey.jobKey(jobPlanCode, jobPlanCode));   // 删除任务
            return basicMapBuilder("success", "删除作业成功", "200");
        } catch (Exception e){
            e.printStackTrace();
            return basicMapBuilder("failed", "删除作业失败","500");
        }
    }

    /**
     * @Author: RedRush
     * @Date:   2020/8/28 10:01
     * @description: 启动一个任务
     */
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public Map<String, Object> startJob(Quartz quartz) {
        // ParamValue参数非空判断
        if (quartz.getParamValue() == null || "".equals(quartz.getParamValue()) || quartz.getParamValue().length() == 0){
            return basicMapBuilder("warning", "参数为空", "502");
        }
        // 前端参数ParamValue： {IP:10.1.18.56,endDate:2019-10-23 15:10:20,startDate:2019-10-23 15:10:19}
//        Map<String, Object> paramValues = JSONObject.parseObject(quartz.getParamValue());
        String IP = quartz.getParamValue();
        // // paramvalue中根据key获取IP，并校验
        String localIP = DateUtil.getLocalHostIP();
//        if (!(paramValues.containsKey("IP") && localIP.equals(paramValues.get("IP")))){
//            return basicMapBuilder("ipError", "IP异常", "503");
//        }
        // 提取jobPlanCode,获取作业数据
        String jobPlanCode = quartz.getJobPlanCode();
        Quartz queryQt = quartzMapper.selectJobPlanClass(jobPlanCode);  // 查询作业内容，job类名
        Map<String, Object> resultMap = new HashMap<>();
        try {
            // 将任务添加至调度器中,修改作业状态为启动
            addQuartz(queryQt);
            quartzMapper.startJobPlanDef(jobPlanCode);
            resultMap = basicMapBuilder("success", "作业启动成功", "200");
        } catch (Exception e){
            resultMap = basicMapBuilder("failed", "作业启动失败", "500");
        }
        // 查询计划运行状态,并添加到resultMap中
        String runState = quartzMapper.selectJobPlanState(jobPlanCode).getRunState();
        resultMap.put("runState", runState);
        resultMap.put("jobPlanCode", jobPlanCode);
        return resultMap;
    }

    /**
     * @Author: RedRush
     * @Date:   2020/8/28 10:41
     * @description: 停止一个任务
     */
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public Map<String, Object> stopJob(Quartz quartz) {
        // ParamValue参数非空判断
        if (quartz.getParamValue() == null || "".equals(quartz.getParamValue()) || quartz.getParamValue().length() == 0){
            return basicMapBuilder("warning", "参数为空", "502");
        }
        // 前端参数ParamValue： {IP:10.1.18.56,endDate:2019-10-23 15:10:20,startDate:2019-10-23 15:10:19}
//        Map<String, Object> paramValues = JSONObject.parseObject(quartz.getParamValue());
        String IP = quartz.getParamValue();
        // paramvalue中根据key获取IP，并校验
        String localIP = DateUtil.getLocalHostIP();
//        if (!(paramValues.containsKey("IP") && localIP.equals(paramValues.get("IP")))){
//            return basicMapBuilder("ipError", "IP异常", "503");
//        }
        // 获取jobPlanCode，创建调度容器，根据jonPlanCode生成触发器key
        String jobPlanCode = quartz.getJobPlanCode();
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        TriggerKey triggerKey = TriggerKey.triggerKey(jobPlanCode, jobPlanCode);
        Map<String,Object> resultMap = new HashMap<>();
        try {
            // 根据触发器的key，停止触发器，移除触发器，删除任务
            scheduler.pauseTrigger(triggerKey);     // 停止触发器
            scheduler.unscheduleJob(triggerKey);    // 移除触发器
            scheduler.deleteJob(JobKey.jobKey(jobPlanCode, jobPlanCode));   //删除任务
            // 修改作业状态为停止
            quartzMapper.stopJobPlanDef(jobPlanCode);
        } catch (Exception e){
            e.printStackTrace();
            resultMap = basicMapBuilder("success", "停止作业成功", "500");
        }
        // 查询计划运行状态,并添加到resultMap中
        String runState = quartzMapper.selectJobPlanState(jobPlanCode).getRunState();
        resultMap.put("runState", runState);
        resultMap.put("jobPlanCode", jobPlanCode);
        return resultMap;
    }

    /**
     * @Author: RedRush
     * @Date:   2020/8/28 11:01
     * @description: 计划回显
     */
    @Override
    public Map<String, Object> selectReturnView(Quartz quartz) {
        Map<String,Object> map=new HashMap<String,Object>();
//        map.put("returnView", quartzMapper.selectReturnView(quartz));
        map.put("paramByCode", quartzMapper.selectParamByCode(quartz));
        return map;
    }

    /**
     * @Author: RedRush
     * @Date:   2020/8/28 11:17
     * @description: 修改一个任务
     */
    @Override
    public Map<String, Object> updateJobPlanAndParam(Quartz quartz) {
        // ParamValue参数非空判断
        if (quartz.getParamValue() == null || "".equals(quartz.getParamValue()) || quartz.getParamValue().length() == 0){
            return basicMapBuilder("warning", "参数为空", "502");
        }
        // 前端参数ParamValue： {IP:10.1.18.56,endDate:2019-10-23 15:10:20,startDate:2019-10-23 15:10:19}
        Map<String, Object> paramValues = JSONObject.parseObject(quartz.getParamValue());
        // // paramvalue中根据key获取IP，并校验
        String localIP = DateUtil.getLocalHostIP();
//        if (!(paramValues.containsKey("IP") && localIP.equals(paramValues.get("IP")))){
//            return basicMapBuilder("ipError", "IP异常", "503");
//        }
        // 任务结束日期校验
        if (quartz.getEndDate() == null || "".equals(quartz.getEndDate())){ // 如果任务结束日期为空，则赋值为无穷
            quartz.setStartDate("9999-12-31 00:00:00");
        }
        // 初始化修改日期
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        quartz.setMakeDate(date);     // 设置更改日期
        quartz.setModifyDate(date);     // 设置更改日期
        quartz.setUseFlag("0");         // 作业计划-停止状态
        quartz.setTriggerState("1");    // 运行状态-等待运行
        // 生成cron指令
        quartz.setCronExp(QuartzUtils.cron(quartz));
        quartzMapper.updateJobPlan(quartz);
        quartzMapper.deletePlanParam(quartz);
        List<Quartz> dataList = quartzMapper.selectJobParam(quartz);
        for (String paramKey : paramValues.keySet()) {
            for (Quartz qtData: dataList) {
                if (qtData.getParamCode().equals(paramKey)){
                    quartz.setParamCode(qtData.getParamCode());
                    quartz.setParamValue((String) paramValues.get(paramKey));
                    quartzMapper.insertJobPlanParam(quartz);
                    System.out.println(quartz.getParamCode() + "," + quartz.getParamValue());
                }
            }
        }
        // 创建resultMap，获取jobPlanCode
        Map<String,Object> resultMap = new HashMap<>();
        String jobPlanCode = quartz.getJobPlanCode();
        try {
            // 创建调度容器，根据jonPlanCode生成触发器key
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            TriggerKey triggerKey = TriggerKey.triggerKey(jobPlanCode, jobPlanCode);
            // 根据触发器的key，停止触发器，移除触发器，删除任务
            scheduler.pauseTrigger(triggerKey);     // 停止触发器
            scheduler.unscheduleJob(triggerKey);    // 移除触发器
            scheduler.deleteJob(JobKey.jobKey(jobPlanCode, jobPlanCode));   //删除任务
            resultMap = basicMapBuilder("success", "执行计划修改成功", "200");
        } catch (Exception e){
            e.printStackTrace();
            resultMap = basicMapBuilder("failed", "执行计划更新失败", "500");
        }
        // 查询计划运行状态,并添加到resultMap中
        String runState = quartzMapper.selectJobPlanState(jobPlanCode).getRunState();
        resultMap.put("runState", runState);
        resultMap.put("jobPlanCode", jobPlanCode);
        return resultMap;
    }

    /**
     * @Author: RedRush
     * @Date:   2020/8/25 10:04
     * @description: 将任务添加至调度器中
     */
    public void addQuartz(Quartz quartz) throws Exception {
        // 根据jobPlanCode确定唯一的作业名称，作业组名，触发器名称，触发器组名
        String jobCode = quartz.getJobCode();           // 获取作业code
        String jobPlanCode = quartz.getJobPlanCode();   // 获取作业计划code
        String runType = quartz.getRunType();           // 获取任务类别(0为定时任务，1为指定间隔任务)
        String className = quartz.getJobClassName();    // 获取要执行的job类名
        Class<?> clazz = Class.forName(className);      // 根据job类名生成Class类
        // 加载日期格式化工具，并格式化起止日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startDate = sdf.parse(quartz.getStartDate());
        Date endDate = sdf.parse(quartz.getEndDate());
        if ("0".equals(runType)){           // cron定时任务
            // 根据quartz生成cron表达式
            String cron = QuartzUtils.cron(quartz);
            log.info(cron);                                 // 日志记录cron表达式
            addJob_Timer(jobCode, jobPlanCode, jobPlanCode, jobPlanCode, clazz, cron, startDate, endDate);
        } else if ("1".equals(runType)){    // 指定间隔任务
            long interval = getInterval(Integer.parseInt(quartz.getRepeatInterval()),quartz.getRepeatUnit());
            addJob_Interval(jobCode, jobPlanCode, jobPlanCode, jobPlanCode, clazz, interval, startDate, endDate);
        }
    }


    /**
     * @Author: RedRush
     * @Date:   2020/8/25 9:56
     * @description: 获取所有的定时任务，添加至调度器中
     */
//    @Override
//    public void initJobsOnstart() {
//        log.info("开始初始化所有批处理任务");
//        List<Quartz> quartzList = quartzMapper.getAvailableJobPlan();
//        if (quartzList.size() > 0){
//            //遍历任务列表
//            for (Quartz quartz : quartzList) {
//                // 将任务添加至调度器中
//                try {
//                    addQuartz(quartz);
//                } catch (Exception e){
//                    e.printStackTrace();
//                }
//            }
//            log.info("任务调度初始化完成");
//        }
//    }

    /**
     * @Author: RedRush
     * @Date:   2020/8/25 11:00
     * @description: 添加一个定时任务
     * @param:
     *      jobName         任务名
     *      jobGroupName    任务组名
     *      triggerName     触发器名
     *      triggerGroupName触发器组名
     *      jobClass        任务
     *      cron            时间设置，参考quartz说明文档
     * @return: void
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })  // 取消编译器产生的警告
    public void addJob_Timer(String jobName, String jobGroupName, String triggerName, String triggerGroupName, Class jobClass,
                       String cron, Date startDate, Date endDate) throws Exception {
        // 创建JobDetail实例，实例与任务执行类绑定，设置任务名、任务组
        JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobName, jobGroupName).build();
        // 触发器初始化
        TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
        triggerBuilder.withIdentity(triggerName, triggerGroupName);
        // 触发器开始、结束时间
        triggerBuilder.startAt(startDate);
        triggerBuilder.endAt(endDate);
        // 触发器时间设定
        // 如果触发器错过触发时间(手动暂停、服务宕机等)，当启动任务后或服务器回复正常后
        // 任务将从下一次触发时间再执行，否则停止任务再重新启动，或服务器宕机重新启动后任务会立即执行
        triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(cron).withMisfireHandlingInstructionDoNothing());
        // 创建Trigger对象和TriggerListener
        CronTrigger trigger = (CronTrigger)triggerBuilder.build();
        TriggerListener myTriggerListener = new MyTriggerListener();
        // 创建调度容器
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        // 调度容器添加TriggerListener，设置JobDetail和Trigger
        scheduler.getListenerManager().addTriggerListener(myTriggerListener);
        scheduler.scheduleJob(jobDetail, trigger);
        // 启动
        if (!scheduler.isShutdown()){
            scheduler.start();
        }

    }

    /**
     * @Author: RedRush
     * @Date:   2020/8/25 11:16
     * @description: 添加一个指定时间间隔的任务，错过时间会立即补跑
     * @param:
     *      jobName         任务名
     *      jobGroupName    任务组名
     *      triggerName     触发器名
     *      triggerGroupName触发器组名
     *      jobClass        任务
     *      interval        间隔时间(单位：毫秒)
     *      startDate       起始日期
     *      endDate         结束日期
     * @return: void
     */
    public void addJob_Interval(String jobName, String jobGroupName, String triggerName, String triggerGroupName, Class jobClass,
                                long interval,Date startDate,Date endDate){
        try {
            // 任务名，任务组，任务执行类
            JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobName, jobGroupName).build();
            // 触发器初始化
            TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
            triggerBuilder.withIdentity(triggerName, triggerGroupName);
            // 触发器开始、结束时间
            triggerBuilder.startAt(startDate);
            triggerBuilder.endAt(endDate);
            // 触发器时间设定
            triggerBuilder.withSchedule(SimpleScheduleBuilder.simpleSchedule()
                            .withIntervalInMilliseconds(interval)   // 时间间隔
                            //.withRepeatCount(5)                   // 指定执行次数
                            .repeatForever()                        // 永远的执行下去
                            .withMisfireHandlingInstructionNextWithRemainingCount() // 重复次数
                            //.withMisfireHandlingInstructionNextWithExistingCount()
            );
            // 创建Trigger对象和TriggerListener
//            CronTrigger trigger = (CronTrigger) triggerBuilder.build();   // 基于日历的作业调度器
            SimpleTrigger trigger= (SimpleTrigger) triggerBuilder.build();  // 指定时间段内执行一次作业，或指定时间间隔内多次执行作业(精确)
            TriggerListener myTriggerListener = new MyTriggerListener();
            // 创建调度容器
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            // 调度容器添加TriggerListener,设置JobDetail和Trigger
            scheduler.getListenerManager().addTriggerListener(myTriggerListener);
            scheduler.scheduleJob(jobDetail, trigger);
            // 启动
            if (!scheduler.isShutdown()) {
                scheduler.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @Author: RedRush
     * @Date:   2020/8/25 11:25
     * @description: 获取执行间隔，单位超过'天'的,建议使用cron
     * @params:
     *      repeatInterval  循环数值
     *      repeatUnit      循环单位(1-秒，2-分钟，3-小时，4-天，5-月，6-周)
     * @return: long
     */
    public long getInterval(int repeatInterval, String repeatUnit){
        long interval = 0L;
        switch (repeatUnit){
            case "1":
                interval=repeatInterval*1000;
                break;
            case "2":
                interval=repeatInterval*60*1000;
                break;
            case "3":
                interval=repeatInterval*60*60*1000;
                break;
            case "4":
                interval=repeatInterval*24*60*60*1000;
                break;
            case "5":
                interval=repeatInterval*30*24*60*60*1000;
                break;
            case "6":
                interval=repeatInterval*7*24*60*60*1000;
                break;
        }
        return interval;
    }

    /**
     * @Author: RedRush
     * @Date:   2020/8/27 11:15
     * @description: 错误信息生成
     * @param:
     *      status      任务状态
     *      msg         返回错误信息
     *      code        错误码
     * @return: Map<String, Object>
     */
    public Map<String, Object> basicMapBuilder(String status, String msg, String code){
        Map<String,Object> returnMap = new HashMap<>();
        returnMap.put("status", status);
        returnMap.put("msg", msg);
        returnMap.put("code", code);
        return returnMap;
    }

}
