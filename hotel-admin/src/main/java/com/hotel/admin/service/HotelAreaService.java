package com.hotel.admin.service;

import com.hotel.admin.model.HotelArea;
import com.hotel.core.page.MybatisPageHelper;
import com.hotel.core.page.PageRequest;
import com.hotel.core.page.PageResult;
import com.hotel.core.service.CurdService;

import java.util.List;

/**
 * ---------------------------
 * 地区码表 (HotelAreaService)         
 * ---------------------------
 * 作者：  kitty-generator
 * 时间：  2019-04-13 16:24:13
 * 说明：  我是由代码生成器生生成的
 * ---------------------------
 */
public interface HotelAreaService extends CurdService<HotelArea> {
   HotelArea findById(String id);
   public PageResult findAreaPage(PageRequest pageRequest);
}
