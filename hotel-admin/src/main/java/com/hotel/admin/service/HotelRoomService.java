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
