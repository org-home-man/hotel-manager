package com.hotel.admin.controller.sys;

import com.hotel.admin.model.SysSocketMessage;
import com.hotel.admin.service.ISysWebSocketMessageService;
import com.hotel.core.http.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message")
public class SysMessageController {
    @Autowired
    private ISysWebSocketMessageService socketMessageService;

    @RequestMapping("findAll")
    public HttpResult findAll(){
        return HttpResult.ok(socketMessageService.findAll());
    }

    @RequestMapping("update")
    public HttpResult updateStatus(SysSocketMessage socketMessage){
        socketMessageService.update(socketMessage);
        return HttpResult.ok();
    }
    @RequestMapping("findCount")
    public HttpResult findNoReadCount(){
        return HttpResult.ok(socketMessageService.findNoReadCount());
    }


}
