package com.hotel.admin.service;

import com.hotel.admin.dto.HotelRoomQry;
import com.hotel.admin.model.BizPrise;
import com.hotel.core.service.CurdService;

import java.util.List;

/**
 * ---------------------------
 * 客房牌价表 (BizPriseService)         
 * ---------------------------
 * 作者：  kitty-generator
 * 时间：  2019-04-08 16:15:59
 * 说明：  我是由代码生成器生生成的
 * ---------------------------
 */
public interface BizPriseService extends CurdService<BizPrise> {
    BizPrise findById(String id);

    List<BizPrise> findByDate(HotelRoomQry record);
}
