package com.rrx.kaoqins.core.web.interceptor;

import cn.hutool.core.util.StrUtil;
import com.rrx.kaoqins.core.exception.LoginException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author JQQ
 */
@Slf4j
@Component
public class SysLoginInterceptor extends HandlerInterceptorAdapter {

    /*
     * 进入controller层之前拦截请求
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {
        System.out.println("getContextPath:" + request.getContextPath());
        System.out.println("getServletPath:" + request.getServletPath());
        System.out.println("getRequestURI:" + request.getRequestURI());
        System.out.println("getRequestURL:" + request.getRequestURL());
        System.out.println("getRealPath:" + request.getSession().getServletContext().getRealPath("image"));
        if(StrUtil.isBlank(request.getParameter("token"))){
            log.error("登录失败");
            //throw new LoginException("登录失败");
        }
        return true;
    }

}
