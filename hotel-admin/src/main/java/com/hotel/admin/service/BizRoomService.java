package com.hotel.admin.service;

import com.hotel.admin.model.BizProPrice;
import com.hotel.admin.model.BizRoom;
import com.hotel.core.page.Page;
import com.hotel.core.page.PageRequest;
import com.hotel.core.page.SimplePageReq;
import com.hotel.core.service.CurdService;

import java.util.Map;

/**
 * ---------------------------
 * 客房信息表 (BizRoomService)         
 * ---------------------------
 * 作者：  kitty-generator
 * 时间：  2019-04-01 21:00:17
 * 说明：  我是由代码生成器生生成的
 * ---------------------------
 */
public interface BizRoomService extends CurdService<BizRoom> {


    /**
     * 根据用户输入的日期或日期范围生成相应日期的数据
     */
    Map productDatePrice(BizProPrice bizProPrice);


    Map producePriceCalendar(BizProPrice bizProPrice);

    Page findPagePara();
}
