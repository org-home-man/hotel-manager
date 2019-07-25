package com.hotel.admin.service;

import com.hotel.admin.dto.BizProInv;
import com.hotel.admin.dto.BizRoomQuery;
import com.hotel.admin.model.BizProPrice;
import com.hotel.admin.model.BizRoom;
import com.hotel.core.page.Page;
import com.hotel.core.page.PageRequest;
import com.hotel.core.page.SimplePageReq;
import com.hotel.core.service.CurdService;

import java.util.Map;

/**
 * ---------------------------
 * 客房信息表 (BizRoomService)         
 * ---------------------------
 */
public interface BizRoomService extends CurdService<BizRoom> {


    /**
     * 根据用户输入的日期或日期范围生成相应日期的数据
     */
    Map productDatePrice(BizProPrice bizProPrice);

    /*
    生成牌价数据
     */
    Map producePriceCalendar(BizProPrice bizProPrice);

    /**
     * 根据用户输入的日期或日期范围生成相应日期的数据(库存)
     */
    Map productDateStock(BizProInv bizProInv);

    /*
    生成库存数据
    */
    Map produceStockCalendar(BizProInv bizProInv);


    Page findPagePara(BizRoomQuery simplePageReq);
}
