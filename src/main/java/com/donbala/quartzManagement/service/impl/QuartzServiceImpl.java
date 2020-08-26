package com.donbala.quartzManagement.service.impl;

import ch.qos.logback.classic.Logger;
import com.donbala.quartzManagement.MyTriggerListener;
import com.donbala.quartzManagement.dao.QuartzMapper;
import com.donbala.quartzManagement.model.JobRunLog;
import com.donbala.quartzManagement.model.Quartz;
import com.donbala.quartzManagement.service.QuartzServiceIntf;
import org.quartz.*;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private QuartzMapper quartzMapper;

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    /**
     * @Author: RedRush
     * @Date:   2020/8/25 9:56
     * @description: 获取所有的定时任务，添加至调度器中
     */
    @Override
    public void initJobsOnstart() {
        log.info("开始初始化所有批处理任务");
        List<Quartz> quartzList = quartzMapper.getAvailableJobPlan();
        if (quartzList.size() > 0){
            //遍历任务列表
            for (Quartz quartz : quartzList) {
                // 将任务添加至调度器中
                try {
                    addQuartz(quartz);
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
            log.info("任务调度初始化完成");
        }
    }

    /**
     * @Author: RedRush
     * @Date:   2020/8/24 18:07
     * @description: 查询全部任务执行计划
     */
    @Override
    public List<Quartz> getJobPlanList() {
        return quartzMapper.getAllJobPlan();
    }

    @Override
    public List<JobRunLog> getJobRunLogList() {
        return quartzMapper.getAllJobRunLog();
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

    @Override
    public Map<String, Object> removeJob(Quartz quartz) {
        return null;
    }

    @Override
    public Map<String, Object> startJob(Quartz quartz) {
        return null;
    }

    @Override
    public Map<String, Object> stopJob(Quartz quartz) {
        return null;
    }

    @Override
    public Map<String, Object> insertJobAndParam(Quartz quartz) {
        return null;
    }

    @Override
    public Map<String, Object> updateJobPlanAndParam(Quartz quartz) {
        return null;
    }

    @Override
    public Map<String, Object> selectReturnView(Quartz quartz) {
        return null;
    }

    /**
     * @Author: RedRush
     * @Date:   2020/8/25 10:04
     * @description: 将任务添加至调度器中
     */
    public void addQuartz(Quartz quartz) throws Exception {
        // 根据jobPlanCode确定唯一的作业名称，作业组名，触发器名称，触发器组名
        // 获取作业code
        String jobCode = quartz.getJobCode();
        // 获取作业计划code
        String jobPlanCode = quartz.getJobPlanCode();
    }

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
     * @params: qz
     * @return: int
     */
    public long getInterval(Quartz quartz){
        long interval = 0L;
        int repeatInterval= Integer.parseInt(quartz.getRepeatInterval());
        String repeatUnit = quartz.getRepeatUnit();
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

}
