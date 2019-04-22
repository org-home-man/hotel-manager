package com.hotel.admin.config;

import com.hotel.core.context.PageContext;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ProjectName: hotel-admin
 * @ClassName: WebPaginationConfig
 * @Author: cc
 * @Description: 分页拦截器
 * @Date: 2019-04-22 23:59
 * @Version: 1.0
 */
public class WebPaginationConfig implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        PageContext.setPage(null);
        PageContext.init(request);
        return true;
    }
}
