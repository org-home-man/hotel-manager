package com.hotel.admin.controller.sys;

import com.hotel.admin.service.ISysWebSocketMessageService;
import com.hotel.core.http.HttpResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message")
public class SysMessageController {
    private ISysWebSocketMessageService socketMessageService;

    @RequestMapping("findAll")
    public HttpResult findAll(){
        return HttpResult.ok(socketMessageService.findAll());
    }
}
