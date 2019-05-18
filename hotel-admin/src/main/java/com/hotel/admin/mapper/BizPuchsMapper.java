package com.hotel.admin.mapper;

import java.util.List;

import com.hotel.admin.model.BizPuchs;
import com.hotel.admin.model.BizPuchsExt;
import com.hotel.admin.model.BizPuchsExtDto;
import com.hotel.admin.model.BizPuchsUpdate;
import com.hotel.admin.qo.BizPuchsQuery;
import com.hotel.core.mybatis.mapper.AbstractMapper;

/**
 * ---------------------------
 * 订单信息表 (BizPuchsMapper)         
 * ---------------------------
 * 作者：  kitty-generator
 * 时间：  2019-04-30 12:29:00
 * 说明：  我是由代码生成器生生成的
 * ---------------------------
 */
public interface BizPuchsMapper extends AbstractMapper<BizPuchs> {

    List<BizPuchsExtDto> findPage(BizPuchsQuery bizPuchsQuery);
    int updateBizPushs(BizPuchsUpdate bizPuchsQuery);
    int puchsConfirm(BizPuchsUpdate bizPuchsQuery);
    List<BizPuchs> findPageS(BizPuchsUpdate bizPuchsQuery);

}