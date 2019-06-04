package com.hotel.admin.service;

import com.hotel.admin.dto.HotelRoomQry;
import com.hotel.admin.model.BizHotl;
import com.hotel.admin.model.BizRecommendRoom;
import com.hotel.admin.model.BizRoom;
import com.hotel.core.page.Page;
import com.hotel.core.service.IService;

import java.util.List;

/**
 * ---------------------------
 * 客房信息表 (BizRoomService)         
 * ---------------------------
 * 作者：  kitty-generator
 * 时间：  2019-04-01 21:00:17
 * 说明：  我是由代码生成器生生成的
 * ---------------------------
 */
public interface HotelRoomService extends IService<BizRoom> {

    /**
     * 酒店条件检索客房信息
     * @param HotelRoomQry
     * @return
     */
    Page findPagePara(HotelRoomQry HotelRoomQry);

    List<BizHotl> findLikeByName(String name);

    List<BizRecommendRoom> findCustroomInfo();
}
