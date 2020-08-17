package com.donbala.Commons.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

/**
 * {\_/}
 * ( ^.^ )
 * / > @ zmf
 * 工具类
 * @date 2019/9/20
 */
public class Common {
    private static Logger logger = LoggerFactory.getLogger(Common.class);

    /**
     * 查询成功返回值，msg需要自己重新put一下，rows要么为空，要么为List<T>
     * @param obj
     * @return
     */
    public static HashMap<String,Object> getSuccess(Object obj){
        HashMap<String,Object> map = new HashMap<String,Object>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        map.put("code", "200");
        map.put("status", "ok");
        map.put("msg", "查询成功");
        map.put("operateTime", sdf.format(new Date()));
        map.put("rows", obj);
        return map;
    }

    public static HashMap<String,Object> insertSuccess(String msg){
        HashMap<String,Object> map = new HashMap<String,Object>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        map.put("code", "200");
        map.put("status", "ok");
        map.put("msg", msg);
        map.put("operateTime", sdf.format(new Date()));
        return map;
    }

    public static HashMap<String,Object> insertFail(String msg){
        HashMap<String,Object> map = new HashMap<String,Object>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //map.put("code", "201");
        map.put("status", "fail");
        map.put("msg", msg);
        map.put("operateTime", sdf.format(new Date()));
        //map.put("rows", "");
        return map;
    }
    /**
     * 删除成功返回值，msg需要自己重新put一下
     * @param obj
     * @return
     */
    public static HashMap<String,Object> deleteSuccess(Object obj){
        HashMap<String,Object> map = new HashMap<String,Object>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        map.put("code", "200");
        map.put("status", "ok");
        map.put("msg", "删除成功");
        map.put("operateTime", sdf.format(new Date()));
        map.put("rows", obj);
        return map;
    }


    public static void templateDown(HttpServletResponse response, String fileName, String filePath) {
        try {
            //设置要下载的文件的名称
            response.setHeader("content-disposition","attachment;filename="+ URLEncoder.encode(fileName, "utf-8"));
            File file = new File(filePath);
            FileInputStream input = new FileInputStream(file);
            OutputStream out = response.getOutputStream();
            byte[] b = new byte[1024];
            int len;
            while ((len = input.read(b)) != -1) {
                out.write(b, 0, len);
            }
            //修正 Excel在“xxx.xlsx”中发现不可读取的内容。是否恢复此工作薄的内容？如果信任此工作簿的来源，请点击"是"
            response.setHeader("Content-Length", String.valueOf(input.getChannel().size()));
            input.close();
            out.close();
            logger.info("应用导入模板下载完成");
        } catch (Exception ex) {
            logger.error("templateDown :", ex);
        }
    }
    /*
     * 生成随机文件名
     */
    public static String generateRandomFilename(){
        String RandomFilename = "";
        Random rand = new Random();//生成随机数
        int random = rand.nextInt();

        Calendar calCurrent = Calendar.getInstance();
        int intDay = calCurrent.get(Calendar.DATE);
        int intMonth = calCurrent.get(Calendar.MONTH) + 1;
        int intYear = calCurrent.get(Calendar.YEAR);
        String now = String.valueOf(intYear) + "_" + String.valueOf(intMonth) + "_" +
                String.valueOf(intDay) + "_";
        logger.debug("生成于今日的文件名前缀为："+now);

        RandomFilename = now + String.valueOf(random > 0 ? random : ( -1) * random) + ".";

        return RandomFilename;
    }
    /*
     * 生成随机文件名
     */
    public static String randomFileName(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HHmmsssss");
        return sdf.format(date);
    }
    /**
     * 生成上传文件的存储地址
     * @param request
     * @param path
     * @return
     */
    public static String uploadPath(HttpServletRequest request, String path) {
        String path1 [] = request.getSession().getServletContext().getRealPath("").split("webapps");
        String path2 = path1[0]+path;
        return path2;
    }
}
