package com.hotel.admin.service;

import com.hotel.admin.dto.BizHotelQueryDto;
import com.hotel.admin.model.BizHotl;
import com.hotel.core.page.Page;
import com.hotel.core.service.CurdService;
import com.hotel.core.service.IService;

import java.util.List;


/**
 * ---------------------------
 * 酒店信息表 (BizHotlService)         
 * ---------------------------
 * 作者：  kitty-generator
 * 时间：  2019-03-30 17:15:22
 * 说明：  我是由代码生成器生生成的
 * ---------------------------
 */
public interface BizHotlService extends IService<BizHotl> {
//    BizHotl findByName(String username);
    List<BizHotl> findAllData(BizHotl bizHotl);
    Page findPage(BizHotelQueryDto dto);
}
