package com.hotel.admin.service;

import com.hotel.admin.entity.Demo;
import com.hotel.core.page.Page;

import java.util.List;

/**
 * @ProjectName: hotel-admin
 * @ClassName: IDemoService
 * @Author: cc
 * @Description: 测试类
 * @Date: 2019-04-22 23:56
 * @Version: 1.0
 */
public interface IDemoService {
    Page findPage(String name);

    List<Demo> selectAll();

}
