package com.hotel.admin.controller.sys;

import com.hotel.admin.websocket.WebSocketServer;
import com.hotel.core.http.HttpResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/checkcenter")
public class CheckCenterController {

    //推送数据接口
    @RequestMapping("/socket/push")
    public HttpResult pushToWeb(String message) {

            WebSocketServer.sendMessage(message);
        return HttpResult.ok();
    }
}
