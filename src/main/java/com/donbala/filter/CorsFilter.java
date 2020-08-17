package com.donbala.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * {\_/}
 * ( ^.^ )
 * / > @ zmf
 *
 * @date 2019/11/1
 */
@WebFilter(filterName="CorsFilter",urlPatterns="/*")
public class CorsFilter implements Filter {

    @Override
    public void destroy() {

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
		   HttpServletRequest request = (HttpServletRequest) req;
		   HttpServletResponse response = (HttpServletResponse) res;
            System.out.println(request.getHeader("Origin"));
		   response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
	       response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE,PUT");
	       //Access-Control-Allow-Headers配置允许前端提交的请求头信息
	       response.addHeader("Access-Control-Allow-Headers", "Origin,X-Requested-With,Content-Type,Authorization");
	       //Access-Control-Expose-Headers配置允许前端获取到的请求头信息
	       response.addHeader("Access-Control-Expose-Headers", "GID,SID");
	       //支持Cookie,使用cookie时 Access-Control-Allow-Origin 不可以设置为*；必须设置为具体值。
	       response.setHeader("Access-Control-Allow-Credentials","true");
   		   //此行代码确保请求可以继续执行至Controller
	       chain.doFilter(req, res);

    }
}
