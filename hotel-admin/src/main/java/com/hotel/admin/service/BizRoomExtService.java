package com.hotel.admin.service;

import com.hotel.admin.model.BizRoomExt;
import com.hotel.core.service.CurdService;

/**
 * ---------------------------
 * 客房信息铺表 (BizRoomExtService)         
 * ---------------------------
 */
public interface BizRoomExtService extends CurdService<BizRoomExt> {

    BizRoomExt findById(String id);

}
