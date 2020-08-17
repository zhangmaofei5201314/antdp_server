package com.donbala.filter;

import com.alibaba.fastjson.JSONObject;
import com.donbala.userManagement.model.CmsUser;
import com.donbala.util.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * {\_/}
 * ( ^.^ )
 * / > @ zmf
 *
 * @date 2019/10/28
 */
@WebFilter(filterName = "tokenFilter", urlPatterns = {"/controller/*"})
public class TokenFilter implements Filter {
    @Autowired
    private CacheManager cache;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
//    @ResponseBody
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest)servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json; charset=utf-8");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //当前时间
        Date currentDate = new Date();
        //根据传过来的token验证
        String token = httpServletRequest.getParameter("token"); //HttpServletRequest

        PrintWriter out = null ;
        JSONObject res = new JSONObject();
        System.out.println("页面的token："+token);
        //如果前台token不为空
        if(token!=null && !token.equals("")) {
            Map<String, Object> tokenCache = (Map<String, Object>) cache.getValue(token);
            //如果缓存有token
            if (tokenCache != null && !tokenCache.equals("")) {
                String tokenOldDate = (String) tokenCache.get(token);
                CmsUser userModel = (CmsUser) tokenCache.get("user");
                //登陆时间
                Date tokenLastDate = null;
                try {
                    tokenLastDate = sdf.parse(tokenOldDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                //计算差值，分钟数
                long minutes = (currentDate.getTime() - tokenLastDate.getTime()) / (1000 * 60);
                //设置超时时间为60分钟
                if (minutes < 60) {
                    //更新缓存里的操作时间
                    tokenCache.put(token, sdf.format(currentDate));
                    tokenCache.put("user", userModel);//用户获取不到问题
                    cache.addCache(token, tokenCache);
                    //正常请求
                    filterChain.doFilter(servletRequest, servletResponse);

                } else {
                    res.put("status", "fail");
                    res.put("msg", "用户登录已超时，请重新登陆！");
                    res.put("code", "402");
                    res.put("operateTime", sdf.format(currentDate));
                    res.put("data", "");
                    out = httpServletResponse.getWriter();
                    out.append(res.toString());
                }


            } else {
                res.put("status", "fail");
                res.put("msg", "用户未登陆！");
                res.put("code", "402");
                res.put("operateTime", sdf.format(currentDate));
                res.put("data", "");
                out = httpServletResponse.getWriter();
                out.append(res.toString());
            }
        }else{
            res.put("status", "fail");
            res.put("msg", "用户未登陆！");
            res.put("code", "402");
            res.put("operateTime", sdf.format(currentDate));
            res.put("data", "");
            out = httpServletResponse.getWriter();
            out.append(res.toString());
        }

    }

    @Override
    public void destroy() {

    }
}
