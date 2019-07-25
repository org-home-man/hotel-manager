package com.hotel.admin.service;

import com.hotel.admin.dto.HotelRoomQry;
import com.hotel.admin.model.BizPrise;
import com.hotel.core.service.CurdService;

import java.util.List;

/**
 * ---------------------------
 * 客房牌价表 (BizPriseService)         
 * ---------------------------
 */
public interface BizPriseService extends CurdService<BizPrise> {
    BizPrise findById(String id);

    List<BizPrise> findByDate(HotelRoomQry record);
}
