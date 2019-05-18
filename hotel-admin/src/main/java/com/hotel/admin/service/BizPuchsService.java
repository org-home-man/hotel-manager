package com.hotel.admin.service;

import com.hotel.admin.model.BizPuchs;
import com.hotel.admin.qo.BizPuchsQuery;
import com.hotel.core.service.IService;

import java.util.List;

/**
 * ---------------------------
 * 订单信息表 (BizPuchsService)         
 * ---------------------------
 * 作者：  kitty-generator
 * 时间：  2019-04-30 12:29:00
 * 说明：  我是由代码生成器生生成的
 * ---------------------------
 */
public interface BizPuchsService extends NewCurdService<BizPuchs> {
    int updateInfo(BizPuchs record);

    List<BizPuchs> findPage(BizPuchsQuery bizPuchsQuery);

    int orderCancel(BizPuchs bizPuchs);
}
