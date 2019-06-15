package com.hotel.admin.controller;

import com.hotel.admin.entity.Demo;
import com.hotel.admin.service.IDemoService;
import com.hotel.admin.util.ExcelUtils;
import com.hotel.core.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Page findPage(String name){
        return demoService.findPage(name);
    }
    @RequestMapping("exportExcel")
    public void exportExcel(HttpServletResponse response) throws IOException {
        List<Demo> list = demoService.selectAll();
        Map<String,List<Demo>> map = new HashMap<>();
        map.put("sheet1",list);
        ExcelUtils.writeMoreSheetExcel(response,map,Demo.class,"aa");
        return ;
    }
}
