package com.hotel.admin.service;

import com.hotel.admin.model.BizPuchsExt;
import com.hotel.core.service.CurdService;

/**
 * ---------------------------
 * 订单信息辅助表 (BizPuchsExtService)         
 * ---------------------------
 */
public interface BizPuchsExtService extends CurdService<BizPuchsExt> {
    BizPuchsExt findById(String id);

}
