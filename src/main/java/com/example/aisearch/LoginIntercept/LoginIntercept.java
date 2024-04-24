package com.example.aisearch.LoginIntercept;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 作者：韦作旭
 * 时间：2024-04-24 22:03
 * 描述：
 **/
@Component
public class LoginIntercept implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //1.得到session对象
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            //说明已经登陆，可以放行
            return true;
        }
        // 执行到这行表示未登录，未登录就重定向到到登陆页面
        response.sendRedirect("/login");
        return false;
    }
}