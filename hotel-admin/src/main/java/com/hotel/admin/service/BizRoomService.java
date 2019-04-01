package com.hotel.admin.service;

import com.hotel.admin.model.BizRoom;
import com.hotel.core.service.CurdService;

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
     * 根据ID查询
     * @param id
     * @return
     */
    BizRoom  findById(String id);


}
