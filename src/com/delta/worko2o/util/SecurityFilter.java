package com.delta.worko2o.util;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: john
 * Date: 15-6-4
 * Time: 下午9:03
 * To change this template use File | Settings | File Templates.
 */
public class SecurityFilter
        implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        // 获得用户请求的URI
        String path = req.getRequestURI();
        if (path.indexOf("employer_detail_r.jsp") > -1 || path.indexOf("/login.jsp") > -1 || path.indexOf("code.jsp") > -1) {
            chain.doFilter(req, res);
            return;
        }

        HttpSession session = req.getSession(true);
        //从session里取的用户名信息
        String name = (String) session.getAttribute("username");

        //判断如果没有取到用户信息,就跳转到登陆页面
        if (name == null || "".equals(name)) {
            //跳转到登陆页面
            res.sendRedirect("/login.jsp");
        } else {
            //已经登陆,继续此次请求
            chain.doFilter(request, response);
        }
    }

    public void destroy() {
    }
}