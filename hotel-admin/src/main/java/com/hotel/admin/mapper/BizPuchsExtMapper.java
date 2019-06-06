package com.hotel.admin.mapper;

import com.hotel.admin.model.BizPuchsExt;
import com.hotel.core.mybatis.mapper.AbstractMapper;

/**
 * ---------------------------
 * 订单信息辅助表 (BizPuchsExtMapper)         
 * ---------------------------
 * 作者：  kitty-generator
 * 时间：  2019-04-30 12:34:59
 * 说明：  我是由代码生成器生生成的
 * ---------------------------
 */
public interface BizPuchsExtMapper extends AbstractMapper<BizPuchsExt> {

    int updateByUnique(BizPuchsExt record);
}