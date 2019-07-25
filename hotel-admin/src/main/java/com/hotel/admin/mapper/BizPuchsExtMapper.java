package com.hotel.admin.mapper;

import com.hotel.admin.model.BizPuchsExt;
import com.hotel.core.mybatis.mapper.AbstractMapper;

/**
 * ---------------------------
 * 订单信息辅助表 (BizPuchsExtMapper)         
 * ---------------------------
 */
public interface BizPuchsExtMapper extends AbstractMapper<BizPuchsExt> {

    int updateByUnique(BizPuchsExt record);
}