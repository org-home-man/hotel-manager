package com.hotel.admin.config;

import com.hotel.admin.redis.UserInfoCache;
import com.hotel.admin.service.SysLogService;
import com.hotel.admin.service.SysUserService;
import com.hotel.admin.websocket.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebSocketConfig {

//    @Bean
//    public ServerEndpointExporter serverEndpointExporter(){
//        return new ServerEndpointExporter();
//    }
    @Autowired
    public void setUserInfoCache(UserInfoCache userInfoCache){
        WebSocketServer.userInfoCache = userInfoCache;
    }

    @Autowired
    public void setSysUserMapper(SysUserService sysUserService){WebSocketServer.sysUserService = sysUserService;}

    @Autowired
    public void setSysLogService(SysLogService sysLogService){WebSocketServer.sysLogService = sysLogService;}
}
