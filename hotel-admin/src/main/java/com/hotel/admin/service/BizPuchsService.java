package com.hotel.admin.service;

import com.hotel.admin.model.BizPuchs;
import com.hotel.admin.model.BizPuchsExt;
import com.hotel.core.service.CurdService;

/**
 * ---------------------------
 * 订单信息表 (BizPuchsService)         
 * ---------------------------
 * 作者：  kitty-generator
 * 时间：  2019-04-30 12:29:00
 * 说明：  我是由代码生成器生生成的
 * ---------------------------
 */
public interface BizPuchsService extends CurdService<BizPuchs> {
     BizPuchs findById(String id);
}
