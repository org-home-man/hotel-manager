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
 * 作者：  kitty-generator
 * 时间：  2019-04-08 16:15:59
 * 说明：  我是由代码生成器生生成的
 * ---------------------------
 */
public interface BizInvService extends NewCurdService<BizInv> {

    List<BizInv> findCancelBizInv(BizPuchs bizPuchs);

    void update(BizInv bizInv);
}
