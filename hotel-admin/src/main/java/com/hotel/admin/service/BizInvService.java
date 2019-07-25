package com.hotel.admin.service;


import com.hotel.admin.model.BizInv;
import com.hotel.admin.model.BizPuchs;
import com.hotel.core.service.CurdService;
import com.hotel.core.service.NewCurdService;

import java.util.List;

/**
 * ---------------------------
 * 客房库存表 (BizInvService)         
 * ---------------------------
 */
public interface BizInvService extends NewCurdService<BizInv> {

    BizInv findByRoomCode(BizInv record);

    List<BizInv> findCancelBizInv(BizPuchs bizPuchs);

    int addByUser(BizInv code);
    void update(BizInv bizInv);

    /**
     * 通过订单查询 房间库存
     * @param bizPuchs
     * @return
     */
    Integer findInventory(BizPuchs bizPuchs);

}
