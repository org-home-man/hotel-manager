package com.hotel.admin.service;

import com.hotel.admin.model.CrtId;
import com.hotel.core.service.CurdService;
/**
 * ---------------------------
 * 自增序列表 (CrtIdService)         
 * ---------------------------
 * 作者：  kitty-generator
 * 时间：  2019-04-05 14:24:29
 * 说明：  我是由代码生成器生生成的
 * ---------------------------
 */
public interface CrtIdService extends CurdService<CrtId> {
    CrtId findById(String crttype);
}
