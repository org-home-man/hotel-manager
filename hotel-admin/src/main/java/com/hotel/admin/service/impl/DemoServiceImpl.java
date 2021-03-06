package com.hotel.admin.service.impl;

import com.hotel.admin.entity.Demo;
import com.hotel.admin.mapper.DemoMapper;
import com.hotel.admin.model.BizRoom;
import com.hotel.admin.service.IDemoService;
import com.hotel.admin.util.IdUtils;
import com.hotel.core.context.PageContext;
import com.hotel.core.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ProjectName: hotel-admin
 * @ClassName: DemoServiceImpl
 * @Author: cc
 * @Description: 测试类
 * @Date: 2019-04-23 0:13
 * @Version: 1.0
 */
@Service
public class DemoServiceImpl implements IDemoService {

    @Autowired
    private DemoMapper demoMapper;
    @Autowired
    private IdUtils idUtils;

    @Override
    public Page findPage(String name) {
//        Page page = PageContext.getPage();
        List<Demo> demos = demoMapper.selectBy();
//        demos.forEach( d -> d.setName("aaa"));
//        page.setRows(demos);
        BizRoom bizRoom = new BizRoom();
        bizRoom.setHotelCode("111");
        System.out.println("id生成:" + idUtils.generateRoomCode(bizRoom));
        return null;
    }

    @Override
    public List<Demo> selectAll() {
        List<Demo> list = demoMapper.selectAll();
        return list;
    }
}
