package com.donbala.quartzManagement.util;

import com.donbala.quartzManagement.model.Quartz;

/**
 * @author RedRush
 * @date 2020/8/27 11:37
 */
public class QuartzUtils {
    public static String cron(Quartz qz) {
        int repeatUnit = Integer.valueOf(qz.getRepeatUnit());//循环单位
        int repeatInterval = Integer.valueOf(qz.getRepeatInterval());//循环间隔数值
        String cron = "";
        String second = "";
        String minute = "";
        String hour = "";
        String day = "";
        if(qz.getStartDate().substring(17, 19).startsWith("0")) {
            second = qz.getStartDate().substring(18, 19);
        }else {
            second = qz.getStartDate().substring(17, 19);
        }
        if(qz.getStartDate().substring(14, 16).startsWith("0")) {
            minute = qz.getStartDate().substring(15, 16);
        }else {
            minute = qz.getStartDate().substring(14, 16);
        }
        if(qz.getStartDate().substring(11, 13).startsWith("0")) {
            hour = qz.getStartDate().substring(12, 13);
        }else {
            hour = qz.getStartDate().substring(11, 13);
        }
        if(qz.getStartDate().substring(8, 10).startsWith("0")) {
            day = qz.getStartDate().substring(9, 10);
        }else {
            day = qz.getStartDate().substring(8, 10);
        }
        if(repeatUnit == 1) {//秒
            cron = second+"/"+repeatInterval + " * * * * ?";
        }else if(repeatUnit == 2) {//分钟
            cron = second+" /"+repeatInterval + " * * * ?";
        }else if(repeatUnit == 3) {//小时
            //例如：cron= "5 50 /5 * * ?",从50分5秒开始，每5小时执行一次
            cron = second+" "+minute+" /"+repeatInterval +" * * ?";
        }else if(repeatUnit == 4) {//日
            //例如：cron= "5 50 5 /6 * ?",从5点50分5秒开始，每6天执行一次
            cron = second+" "+minute+" "+hour+" /"+repeatInterval+" * ?";
        }else if(repeatUnit == 5) {//月
            //例如：cron= "5 50 5 6 /2 ?",从6号5点50分5秒开始，每两个月执行一次
            cron = second+" "+minute+" "+hour+" "+day+" /"+repeatInterval +" ?";
        }else {//周
            //例如：   cron= "10 50 4 * * 1"
            cron = second+" "+minute+" "+hour+" ? * "+repeatInterval;
        }
        return cron;
    }
}
