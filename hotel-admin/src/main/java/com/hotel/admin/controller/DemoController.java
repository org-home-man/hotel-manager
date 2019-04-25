package com.hotel.admin.controller;

import com.hotel.admin.service.IDemoService;
import com.hotel.core.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProjectName: hotel-admin
 * @ClassName: DemoController
 * @Author: cc
 * @Description: 测试类
 * @Date: 2019-04-23 0:14
 * @Version: 1.0
 */
@RestController
@RequestMapping("demo")
public class DemoController {
    @Autowired
    private IDemoService demoService;

    @RequestMapping("page")
    public Page findPage(){
        return demoService.findPage();
    }
}
