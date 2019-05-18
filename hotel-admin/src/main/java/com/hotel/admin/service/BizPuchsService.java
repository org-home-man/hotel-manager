package com.hotel.admin.service;

import com.hotel.admin.model.BizPuchs;
import com.hotel.admin.model.BizPuchsExtDto;
import com.hotel.admin.model.BizPuchsUpdate;
import com.hotel.admin.qo.BizPuchsQuery;
import com.hotel.core.service.IService;
import com.hotel.core.service.NewCurdService;

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
    int updateInfo(BizPuchsUpdate record);

    int puchsConfirm(BizPuchsUpdate record);

    List<BizPuchsExtDto> findPage(BizPuchsQuery bizPuchsQuery);

    int orderCancel(List<BizPuchs> bizPuchs);

    void cancel(BizPuchs bizPuchs);
}
