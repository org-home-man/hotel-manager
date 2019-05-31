package com.hotel.admin.config;

import com.hotel.admin.redis.UserInfoCache;
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
}
