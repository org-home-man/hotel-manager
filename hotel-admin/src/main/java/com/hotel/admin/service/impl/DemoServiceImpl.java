package com.hotel.admin.service.impl;

import com.hotel.admin.mapper.DemoMapper;
import com.hotel.admin.service.IDemoService;
import com.hotel.core.context.PageContext;
import com.hotel.core.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public Page findPage(String name) {
        demoMapper.selectBy();
        return PageContext.getPage();
    }
}
