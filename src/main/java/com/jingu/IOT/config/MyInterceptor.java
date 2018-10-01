package com.jingu.IOT.config;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 *  拦截器实现
 */
public class MyInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        //System.out.println("--------------拦截器请求前调用-----------------------------");
        return true;// 只有返回true才会继续向下执行，返回false取消当前请求
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

        //System.out.println("请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）");

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        //System.out.println("在整个请求结束之后被调用，也就是在DispatcherServlet渲染了对应的视图之后执行（主要是用于进行资源清理工作）");

    }
}
