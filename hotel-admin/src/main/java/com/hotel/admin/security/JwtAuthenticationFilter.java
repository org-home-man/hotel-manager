package com.hotel.admin.security;

import com.alibaba.fastjson.JSON;
import com.hotel.admin.redis.UserInfoCache;
import com.hotel.core.exception.GlobalException;
import com.hotel.core.http.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 登录认证过滤器
 * @author Louis
 * @date Nov 20, 2018
 */
public class JwtAuthenticationFilter extends BasicAuthenticationFilter {


    private static UserInfoCache userInfoCache;


    @Autowired
    public JwtAuthenticationFilter(AuthenticationManager authenticationManager,UserInfoCache userInfoCache) {
        super(authenticationManager);
        this.userInfoCache = userInfoCache;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws GlobalException,IOException, ServletException {
    	// 获取token, 并检查登录状态
        if(userInfoCache.checkAuthentication(request,response)){
            chain.doFilter(request, response);
        }
    }


}