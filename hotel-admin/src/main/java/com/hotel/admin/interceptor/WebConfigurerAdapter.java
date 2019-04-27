package com.hotel.admin.interceptor;

import com.hotel.admin.config.WebPaginationConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @ProjectName: hotel-admin
 * @ClassName: WebConfigurerAdapter
 * @Author: cc
 * @Description: 分页拦截配置类
 * @Date: 2019-04-23 0:06
 * @Version: 1.0
 */
//@Configuration
//public class WebConfigurerAdapter extends WebMvcConfigurationSupport {
//
//    @Bean
//    public HandlerInterceptor getMyInterceptor(){
//        return new WebPaginationConfig();
//    }
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(getMyInterceptor()).addPathPatterns("/**");
//        super.addInterceptors(registry);
//    }
//
//    @Override
//    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/static/**").addResourceLocations("/static/**");
//        super.addResourceHandlers(registry);
//    }
//}
