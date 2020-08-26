package com.donbala.quartzManagement;

import ch.qos.logback.classic.Logger;
import com.donbala.quartzManagement.service.impl.QuartzServiceImpl;
import org.quartz.JobExecutionContext;
import org.quartz.Trigger;
import org.quartz.TriggerListener;
import org.slf4j.LoggerFactory;

/**
 * @author RedRush
 * @date 2020/8/25 14:56
 */
public class MyTriggerListener implements TriggerListener {

    public final static Logger log = (Logger) LoggerFactory.getLogger(QuartzServiceImpl.class);

    @Override
    public String getName() {
        return "myTriggerListener";
    }

    /**
     * Trigger被激发 它关联的job即将被运行
     * Called by the Scheduler when a Trigger has fired, and it's associated JobDetail is about to be executed.
     */
    @Override
    public void triggerFired(Trigger trigger, JobExecutionContext context) {
        log.info("jobCode："+context.getJobDetail().getKey().getName());
        log.info("计划名："+context.getJobDetail().getKey().getGroup());
        log.info("已出发，即将执行...");
    }

    /**
     * Trigger被激发 它关联的job即将被运行,先执行(1)，在执行(2) 如果返回TRUE 那么任务job会被终止
     * Called by the Scheduler when a Trigger has fired, and it's associated JobDetail is about to be executed
     */
    @Override
    public boolean vetoJobExecution(Trigger trigger, JobExecutionContext context) {
        return false;
    }

    /**
     * (3) 当Trigger错过被激发时执行,比如当前时间有很多触发器都需要执行，但是线程池中的有效线程都在工作，
     * 那么有的触发器就有可能超时，错过这一轮的触发。
     * Called by the Scheduler when a Trigger has misfired.
     */
    @Override
    public void triggerMisfired(Trigger trigger) {

    }

    /**
     * (4) 任务完成时触发
     * Called by the Scheduler when a Trigger has fired, it's associated JobDetail has been executed
     * and it's triggered(xx) method has been called.
     */
    @Override
    public void triggerComplete(Trigger trigger, JobExecutionContext context, Trigger.CompletedExecutionInstruction triggerInstructionCode) {
        log.info("作业名："+context.getJobDetail().getKey().getName());
        log.info("计划名："+context.getJobDetail().getKey().getGroup());
        log.info("任务结束，执行完毕...");
    }
}
